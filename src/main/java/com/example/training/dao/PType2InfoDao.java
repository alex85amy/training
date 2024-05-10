package com.example.training.dao;

import com.example.training.bean.PType2Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PType2InfoDao {

    private final Connection conn;
    private final Logger logger = LogManager.getLogger();

    public PType2InfoDao(Connection conn) {
        this.conn = conn;
    }


    public void add(PType2Info pType2Info) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO p_type_2_info(category, name) VALUES (?, ?)")) {
            preparedStatement.setString(1, pType2Info.getCategory());
            preparedStatement.setString(2, pType2Info.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());

        }
    }


    public boolean delete(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "DELETE FROM p_type_2_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
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

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
    }


    public PType2Info findById(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String category = rs.getString("category");
            String name = rs.getString("name");

            return new PType2Info(id, category, name);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


    public List<PType2Info> findAll() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info")) {
            ResultSet rs = preparedStatement.executeQuery();
            List<PType2Info> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String category = rs.getString("category");
                String name = rs.getString("name");
                PType2Info pType2Info = new PType2Info(autoId, category, name);
                list.add(pType2Info);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


    public PType2Info findByCategoryOrName(String category, String name) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info WHERE category = ? OR name= ?")) {
            preparedStatement.setString(1, category);
            preparedStatement.setString(2, name);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int autoId = rs.getInt("auto_id");

            return new PType2Info(autoId, category, name);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
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

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }


    public List<PType2Info> findPageData(int amount, int page) {
        int offset = (page - 1) * amount;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM p_type_2_info LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            List<PType2Info> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String category = rs.getString("category");
                String name = rs.getString("name");
                PType2Info pType2Info = new PType2Info(autoId, category, name);
                list.add(pType2Info);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }

}
