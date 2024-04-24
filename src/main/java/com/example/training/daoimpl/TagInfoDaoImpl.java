package com.example.training.daoimpl;

import com.example.training.bean.TagInfo;
import com.example.training.dao.TagInfoDao;
import com.example.training.util.ResultSetToJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;


public class TagInfoDaoImpl implements TagInfoDao {

    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public TagInfoDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(TagInfo tagInfo) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "INSERT INTO tag_info(tag_id, tag_name, type) " +
                    "VALUES (" + tagInfo.getTagId() + ",'" + tagInfo.getTagName() + "'," + tagInfo.getType() + ")";
            statement.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
        }
    }

    @Override
    public boolean delete(int tagId) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "DELETE FROM tag_info WHERE tag_id =" + tagId;
            statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }

    @Override
    public boolean update(int tagId, TagInfo tagInfo) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "UPDATE tag_info SET tag_name='" + tagInfo.getTagName() + "',type=" + tagInfo.getType() +
                    "WHERE tag_id=" + tagId;
            statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }

    @Override
    public String findByTagId(int tagId) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM tag_info WHERE tag_id=" + tagId;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "tag_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findAll() {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM tag_info";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "tag_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findpagedata(int per_page, int page) {
        int offset = (page - 1) * per_page;
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM tag_info LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "tag_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
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
}
