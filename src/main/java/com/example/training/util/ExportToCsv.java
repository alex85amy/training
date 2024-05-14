package com.example.training.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            List<TagCount> tagCounts = new ArrayList<>();
            while (resultSet.next()) {
                String tagName = resultSet.getString("tag_name");
                int news = resultSet.getInt("新聞");
                int blog = resultSet.getInt("部落格");
                int forum = resultSet.getInt("討論區");
                int social = resultSet.getInt("社群網站");
                int comment = resultSet.getInt("評論");
                int qa = resultSet.getInt("問答網站");
                int video = resultSet.getInt("影音");
                int type = resultSet.getInt("type");
                TagCount tagCount = new TagCount(tagName, news, blog, forum, social, comment, qa, video, type);
                tagCounts.add(tagCount);
            }


            // 添加 UTF-8 BOM 到文件頭確保編碼
            csvWriter.write('\ufeff');

            // Write CSV header
            csvWriter.append(" ").append(",")
                    .append("新聞").append(",")
                    .append("部落格").append(",")
                    .append("討論區").append(",")
                    .append("社群網站").append(",")
                    .append("評論").append(",")
                    .append("問答網站").append(",")
                    .append("影音").append("\n");


            // Write CSV data

            // 使用Stream過濾出type等於1的TagCount物件
            TagCount totalTagCount1 = tagCounts.stream()
                    .filter(tagCount -> tagCount.getType() == 1)
                    // 將符合條件的TagCount物件的各個屬性相加進累加器
                    .reduce(new TagCount(), (accumulator, element) -> {
                        accumulator.setNews(accumulator.getNews() + element.getNews());
                        accumulator.setBlog(accumulator.getBlog() + element.getBlog());
                        accumulator.setForum(accumulator.getForum() + element.getForum());
                        accumulator.setSocial(accumulator.getSocial() + element.getSocial());
                        accumulator.setComment(accumulator.getComment() + element.getComment());
                        accumulator.setQa(accumulator.getQa() + element.getQa());
                        accumulator.setVideo(accumulator.getVideo() + element.getVideo());
                        // type屬性不需要相加
                        accumulator.setType(1);
                        accumulator.setTagName("屬性標籤");
                        return accumulator;
                    });
            // 將統計屬性標籤寫入
            csvWriter.append(totalTagCount1.getTagName()).append(",")
                    .append(String.valueOf(totalTagCount1.getNews())).append(",")
                    .append(String.valueOf(totalTagCount1.getBlog())).append(",")
                    .append(String.valueOf(totalTagCount1.getForum())).append(",")
                    .append(String.valueOf(totalTagCount1.getSocial())).append(",")
                    .append(String.valueOf(totalTagCount1.getComment())).append(",")
                    .append(String.valueOf(totalTagCount1.getQa())).append(",")
                    .append(String.valueOf(totalTagCount1.getVideo())).append("\n");
            // 過濾type等於1的TagCount物件並寫入
            tagCounts.stream()
                    .filter(tagCount -> tagCount.getType() == 1)
                    .forEach(tagCount -> csvWriter.append(tagCount.getTagName()).append(",")
                            .append(String.valueOf(tagCount.getNews())).append(",")
                            .append(String.valueOf(tagCount.getBlog())).append(",")
                            .append(String.valueOf(tagCount.getForum())).append(",")
                            .append(String.valueOf(tagCount.getSocial())).append(",")
                            .append(String.valueOf(tagCount.getComment())).append(",")
                            .append(String.valueOf(tagCount.getQa())).append(",")
                            .append(String.valueOf(tagCount.getVideo())).append("\n"));

            // 使用Stream過濾出type等於2的TagCount物件
            TagCount totalTagCount2 = tagCounts.stream()
                    .filter(tagCount -> tagCount.getType() == 2)
                    // 將符合條件的TagCount物件的各個屬性相加
                    .reduce(new TagCount(), (accumulator, element) -> {
                        accumulator.setNews(accumulator.getNews() + element.getNews());
                        accumulator.setBlog(accumulator.getBlog() + element.getBlog());
                        accumulator.setForum(accumulator.getForum() + element.getForum());
                        accumulator.setSocial(accumulator.getSocial() + element.getSocial());
                        accumulator.setComment(accumulator.getComment() + element.getComment());
                        accumulator.setQa(accumulator.getQa() + element.getQa());
                        accumulator.setVideo(accumulator.getVideo() + element.getVideo());
                        // type屬性不需要相加
                        accumulator.setType(2);
                        accumulator.setTagName("內容標籤");
                        return accumulator;
                    });
            // 將統計內容標籤寫入
            csvWriter.append(totalTagCount2.getTagName()).append(",")
                    .append(String.valueOf(totalTagCount2.getNews())).append(",")
                    .append(String.valueOf(totalTagCount2.getBlog())).append(",")
                    .append(String.valueOf(totalTagCount2.getForum())).append(",")
                    .append(String.valueOf(totalTagCount2.getSocial())).append(",")
                    .append(String.valueOf(totalTagCount2.getComment())).append(",")
                    .append(String.valueOf(totalTagCount2.getQa())).append(",")
                    .append(String.valueOf(totalTagCount2.getVideo())).append("\n");

            // 過濾type等於2的TagCount物件並寫入
            tagCounts.stream()
                    .filter(tagCount -> tagCount.getType() == 2)
                    .forEach(tagCount -> csvWriter.append(tagCount.getTagName()).append(",")
                            .append(String.valueOf(tagCount.getNews())).append(",")
                            .append(String.valueOf(tagCount.getBlog())).append(",")
                            .append(String.valueOf(tagCount.getForum())).append(",")
                            .append(String.valueOf(tagCount.getSocial())).append(",")
                            .append(String.valueOf(tagCount.getComment())).append(",")
                            .append(String.valueOf(tagCount.getQa())).append(",")
                            .append(String.valueOf(tagCount.getVideo())).append("\n"));

            csvWriter.flush();
            // Convert CSV data to byte array
            return outputStream.toByteArray();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }


    private final String insertSQL = "SELECT " +
            "    t.tag_name AS 'tag_name'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'news'  THEN c.source_area_id ELSE NULL END) AS '新聞'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'blog'  THEN c.source_area_id ELSE NULL END) AS '部落格'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'forum'  THEN c.source_area_id ELSE NULL END) AS '討論區'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'social'  THEN c.source_area_id ELSE NULL END) AS '社群網站'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'comment'  THEN c.source_area_id ELSE NULL END) AS '評論'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'qa'  THEN c.source_area_id ELSE NULL END) AS '問答網站'," +
            "    COUNT(DISTINCT CASE WHEN c.p_type_2 = 'video'  THEN c.source_area_id ELSE NULL END) AS '影音'," +
            "    t.type as 'type'" +
            " FROM " +
            "    tag_info t" +
            " LEFT JOIN " +
            "    channel_tag_mapping m ON t.tag_id = m.tag_id" +
            " LEFT JOIN " +
            "    channel_info c ON m.s_area_id = c.source_area_id" +
            " GROUP BY " +
            "    t.tag_name,  t.type" +
            " ORDER BY " +
            "    t.type, t.tag_name;";
}



