package com.example.training.dao;

import com.example.training.bean.TagInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TagInfoDao {

    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public TagInfoDao(Connection conn) {
        this.conn = conn;
    }


    public void add(TagInfo tagInfo) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO tag_info(tag_id, tag_name, type) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, tagInfo.getTagId());
            preparedStatement.setString(2, tagInfo.getTagName());
            preparedStatement.setInt(3, tagInfo.getType());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
        }
    }


    public boolean delete(int tagId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "DELETE FROM tag_info WHERE tag_id = ?")) {
            preparedStatement.setInt(1, tagId);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }


    public boolean update(int tagId, TagInfo tagInfo) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE tag_info SET tag_name= ?, type= ? WHERE tag_id = ?")) {
            preparedStatement.setString(1, tagInfo.getTagName());
            preparedStatement.setInt(2, tagInfo.getTagId());
            preparedStatement.setInt(3, tagId);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }


    public TagInfo findByTagId(int tagId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM tag_info WHERE tag_id = ?")) {
            preparedStatement.setInt(1, tagId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String tagName = rs.getString("tag_name");
            int type = rs.getInt("type");

            return new TagInfo(tagId, tagName, type);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public List<TagInfo> findAll() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM tag_info")) {
            ResultSet rs = preparedStatement.executeQuery();
            List<TagInfo> list = new ArrayList<>();
            while (rs.next()) {
                int tagId = rs.getInt("tag_id");
                String tagName = rs.getString("tag_name");
                int type = rs.getInt("type");
                TagInfo tagInfo = new TagInfo(tagId, tagName, type);
                list.add(tagInfo);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public void addBatch(List<TagInfo> tagInfoList) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO tag_info(tag_id, tag_name, type) VALUES (?, ?, ?)")
        ) {
            int batchSize = 30;//批次數量
            int count = 0; // 計數器，用於計算添加到批次的記錄數量

            for (TagInfo tagInfo : tagInfoList) {
                preparedStatement.setInt(1, tagInfo.getTagId());
                preparedStatement.setString(2, tagInfo.getTagName());
                preparedStatement.setInt(3, tagInfo.getType());
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


    public List<TagInfo> findPageData(int amount, int page) {
        int offset = (page - 1) * amount;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM tag_info LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            List<TagInfo> list = new ArrayList<>();
            while (rs.next()) {
                int tagId = rs.getInt("tag_id");
                String tagName = rs.getString("tag_name");
                int type = rs.getInt("type");
                TagInfo tagInfo = new TagInfo(tagId, tagName, type);
                list.add(tagInfo);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }
}
