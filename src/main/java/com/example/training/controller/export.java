package com.example.training.controller;

import com.company.bean.PType2Info;
import com.company.daoimpl.PType2InfoDaoImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

@RestController
public class export {
        private static final String URL = "jdbc:mysql://localhost:3306/training?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "abc123";
        PType2InfoDaoImpl pType2InfoDao = new PType2InfoDaoImpl();

        @GetMapping(value = "/export", produces = "text/csv")
        public void exportCSV(HttpServletResponse response) throws IOException {
                // 设置HTTP响应头
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"data.csv\"");

                // 创建一些示例数据
                List<PType2Info> dataList = (List<PType2Info>) pType2InfoDao.findAll();

                // 创建CSVWriter并写入数据
                CsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
                String[] header = {"id", "category", "name"};
                csvWriter.writeHeader(header);
                for (PType2Info data : dataList) {
                        csvWriter.write(data, header);
                }
                csvWriter.close();

        }
}

//        public Object findById(int id) {
//                try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                     Statement statement = conn.createStatement()) {
//                        String insertSQL = "SELECT * FROM p_type_2_info WHERE auto_id=" + id;
//                        ResultSet rs = statement.executeQuery(insertSQL);
//                        return ResultSetToJson.ResultSetToJsonObject(rs,"p_type_2_info");
//
//                } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                }
//                return null;
//        }