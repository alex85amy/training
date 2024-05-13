package com.example.training.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExportToCsv {

    private final Connection conn = JDBC.getConn();
    private final Logger logger = LogManager.getLogger();

    public byte[] exportCsv() {

        try (PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
             ResultSet resultSet = preparedStatement.executeQuery();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintWriter csvWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {

//            List<TagCount> list = new ArrayList<>();
//            while (resultSet.next()) {
//                String tagName = resultSet.getString("tag_name");
//                int news = resultSet.getInt("新聞");
//                int blog = resultSet.getInt("部落格");
//                int forum = resultSet.getInt("討論區");
//                int social = resultSet.getInt("社群網站");
//                int comment = resultSet.getInt("評論");
//                int qa = resultSet.getInt("問答網站");
//                int video = resultSet.getInt("影音");
//                int type = resultSet.getInt("type");
//                TagCount tagCount = new TagCount(news, blog, forum, social, comment, qa, video, type);
//                list.add(tagCount);
//            }


            // 添加 UTF-8 BOM 到文件頭確保編碼
            csvWriter.write('\ufeff');

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Write CSV header
            for (int i = 1; i <= columnCount; i++) {
                csvWriter.append(metaData.getColumnName(i));
                if (i < columnCount) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            // Write CSV data
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(resultSet.getString(i));
                    if (i < columnCount) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();

            // Convert CSV data to byte array
            return outputStream.toByteArray();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
            // Handle error
            return null;
        }
    }


    private String insertSQL = "SELECT " +
            "    t.tag_name AS ''," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'news'  THEN c.source_area_id ELSE NULL END) AS '新聞'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'blog'  THEN c.source_area_id ELSE NULL END) AS '部落格'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'forum'  THEN c.source_area_id ELSE NULL END) AS '討論區'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'social'  THEN c.source_area_id ELSE NULL END) AS '社群網站'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'comment'  THEN c.source_area_id ELSE NULL END) AS '評論'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'qa'  THEN c.source_area_id ELSE NULL END) AS '問答網站'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'video'  THEN c.source_area_id ELSE NULL END) AS '影音'" +
            " FROM " +
            "    tag_info t" +
            " LEFT JOIN " +
            "    channel_tag_mapping m ON t.tag_id = m.tag_id" +
            " LEFT JOIN " +
            "    channel_info c ON m.s_area_id = c.source_area_id" +
            " GROUP BY " +
            "    t.tag_name" +
            " ORDER BY " +
            "    MAX(t.type), t.tag_name;";
}



