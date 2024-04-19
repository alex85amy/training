package com.example.training;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class ExportToCsv {
    private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abc123";

    public void exportQueryResultToCsv() {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(insertSQL);
             PrintWriter csvWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("output.csv"), StandardCharsets.UTF_8))) {


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

            System.out.println("CSV file exported successfully!");

        } catch (SQLException | IOException e) {
            System.err.println("Error exporting data to CSV: " + e.getMessage());
        }
    }


    String insertSQL = "SELECT category,tag_type,tag_name,count " +
            "FROM (" +
            "SELECT p.category AS category,t.type AS tag_type,t.tag_name AS tag_name," +
            " IFNULL(COUNT(c.source_area_id), 0) AS count" +
            " FROM p_type_2_info p" +
            " CROSS JOIN tag_info t" +
            " LEFT JOIN" +
            " channel_tag_mapping m ON t.tag_id = m.tag_id" +
            " LEFT JOIN" +
            " channel_info c ON m.s_area_id = c.source_area_id AND c.is_used = 1 AND c.p_type_2 = p.category" +
            " WHERE p.category IS NOT NULL" +
            " GROUP BY p.category, t.tag_name, t.type" +
            " UNION ALL" +
            " SELECT p.category AS category, t.type AS tag_type," +
            " NULL AS tag_name, COUNT(DISTINCT c.source_area_id) AS count" +
            " FROM channel_info c" +
            " JOIN p_type_2_info p ON c.p_type_2 = p.category" +
            " JOIN channel_tag_mapping m ON c.source_area_id = m.s_area_id" +
            " JOIN tag_info t ON m.tag_id = t.tag_id" +
            " GROUP BY p.category, t.type" +
            ")" +
            " AS combined_data" +
            " ORDER BY category, tag_type, tag_name;";
}



