package com.example.training.dao;

import com.example.training.bean.ChannelInfo;
import com.example.training.util.ResultSetToJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChannelInfoDao {

    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public ChannelInfoDao(Connection conn) {
        this.conn = conn;
    }


    public void add(ChannelInfo channelInfo) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO channel_info(source_id, source_area_id, is_used, p_type_2) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, channelInfo.getSourceId());
            preparedStatement.setString(2, channelInfo.getSourceAreaId());
            preparedStatement.setInt(3, channelInfo.getIsUsed());
            preparedStatement.setString(4, channelInfo.getPType2());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
        }
    }


    public boolean delete(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "DELETE FROM channel_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
            return false;
        }
    }


    public boolean update(int id, ChannelInfo channelInfo) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE channel_info SET source_id = ?,source_area_id = ?,is_used = ?,p_type_2 = ? WHERE auto_id= ?")) {
            preparedStatement.setString(1, channelInfo.getSourceId());
            preparedStatement.setString(2, channelInfo.getSourceAreaId());
            preparedStatement.setInt(3, channelInfo.getIsUsed());
            preparedStatement.setString(4, channelInfo.getPType2());
            preparedStatement.setInt(5, id);

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
                "SELECT * FROM channel_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public String findAll() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_info")) {
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public String findBySourceAreaId(String sourceAreaId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_info WHERE source_area_id = ?")) {
            preparedStatement.setString(1, sourceAreaId);
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public void addBatch(List<ChannelInfo> channelInfoList) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO channel_info(source_id, source_area_id, is_used, p_type_2) VALUES (?, ?, ?, ?)")
        ) {
            int batchSize = 1000;//批次數量
            int count = 0; // 計數器，用於計算添加到批次的記錄數量

            for (ChannelInfo channelInfo : channelInfoList) {
                preparedStatement.setString(1, channelInfo.getSourceId());
                preparedStatement.setString(2, channelInfo.getSourceAreaId());
                preparedStatement.setInt(3, channelInfo.getIsUsed());
                preparedStatement.setString(4, channelInfo.getPType2());
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
                "SELECT * FROM channel_info LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, per_page);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

}


