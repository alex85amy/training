package com.example.training.daoimpl;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.util.JDBC;
import com.example.training.util.ResultSetToJson;

import java.sql.*;
import java.util.List;

public class ChannelTagMappingDaoImpl implements ChannelTagMappingDao {

    JDBC jdbc = new JDBC();

    @Override
    public void add(ChannelTagMapping channelTagMapping) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "INSERT INTO channel_tag_mapping(s_area_id, tag_id) " +
                    "VALUES ('" + channelTagMapping.getSourceAreaId() + "'," + channelTagMapping.getTagId() + ")";
            int rowsAffected = statement.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "DELETE FROM channel_tag_mapping WHERE auto_id =" + id;
            int rowsAffected = statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, ChannelTagMapping channelTagMapping) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "UPDATE channel_tag_mapping SET s_area_id='" + channelTagMapping.getSourceAreaId() + "',tag_id=" + channelTagMapping.getTagId() +
                    "WHERE auto_id=" + id;
            int rowsAffected = statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Object findById(int id) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE auto_id =" + id;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonObject(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Object findBySourceAreaId(String sourceAreaId) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE s_area_id ='" + sourceAreaId + "'";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonObject(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Object findByTagId(int tagId) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE tag_id =" + tagId;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonObject(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Object findAll() {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonObject(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBatch(List<ChannelTagMapping> channelTagMappingList) {
        try (Connection conn = jdbc.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "INSERT INTO channel_tag_mapping(s_area_id, tag_id) VALUES (?, ?) "
             )) {

            int batchSize = 1000;//批次數量
            int count = 0; // 計數器，用於計算添加到批次的記錄數量
            for (ChannelTagMapping channelTagMapping : channelTagMappingList) {
                preparedStatement.setString(1, channelTagMapping.getSourceAreaId());
                preparedStatement.setInt(2, channelTagMapping.getTagId());
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
        }
    }
}
