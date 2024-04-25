package com.example.training.dao;

import com.example.training.bean.PType2Info;
import com.example.training.util.ResultSetToJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PType2InfoDao {

    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public PType2InfoDao(Connection conn) {
        this.conn = conn;
    }


    public void add(PType2Info pType2Info) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO p_type_2_info(category, name) VALUES (?, ?)")) {
            preparedStatement.setString(1, pType2Info.getCategory());
            preparedStatement.setString(2, pType2Info.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());

        }
    }


    public boolean delete(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "DELETE FROM p_type_2_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }


    public boolean update(int id, PType2Info pType2Info) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE p_type_2_info SET category= ?, name= ? WHERE auto_id = ?")) {
            preparedStatement.setString(1, pType2Info.getCategory());
            preparedStatement.setString(2, pType2Info.getName());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }


    public String findById(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "p_type_2_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public String findAll() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info")) {
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "p_type_2_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public String findByCategoryOrName(String category, String name) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info WHERE category = ? OR name= ?")) {
            preparedStatement.setString(1, category);
            preparedStatement.setString(2, name);
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "p_type_2_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public void addBatch(List<PType2Info> pType2InfoList) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO p_type_2_info(category, name) VALUES (?, ?)")
        ) {
            int batchSize = 30;//批次數量
            int count = 0; // 計數器，用於計算添加到批次的記錄數量

            for (PType2Info pType2Info : pType2InfoList) {
                preparedStatement.setString(1, pType2Info.getCategory());
                preparedStatement.setString(2, pType2Info.getName());
                preparedStatement.addBatch();// 將操作加入批次
                count++;

                if (count % batchSize == 0) {
                    preparedStatement.executeBatch();// 執行批次操作
                    count = 0; // 重置計數器
                }
            }
            preparedStatement.executeBatch();// 執行最後批次操作

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
        }
    }


    public String findpagedata(int per_page, int page) {
        int offset = (page - 1) * per_page;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, per_page);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "p_type_2_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

}
