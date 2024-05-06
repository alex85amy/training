package com.example.training.dao;

import com.example.training.bean.ChannelInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


    public ChannelInfo findById(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_info WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String sourceId = rs.getString("source_id");
            String sourceAreaId = rs.getString("source_area_id");
            int isUsed = rs.getInt("is_used");
            String pType2 = rs.getString("p_type_2");

            return new ChannelInfo(id, sourceId, sourceAreaId, isUsed, pType2);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public List<ChannelInfo> findAll() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_info")) {
            ResultSet rs = preparedStatement.executeQuery();
            List<ChannelInfo> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String sourceId = rs.getString("source_id");
                String sourceAreaId = rs.getString("source_area_id");
                int isUsed = rs.getInt("is_used");
                String pType2 = rs.getString("p_type_2");
                ChannelInfo channelInfo = new ChannelInfo(autoId, sourceId, sourceAreaId, isUsed, pType2);
                list.add(channelInfo);
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


    public ChannelInfo findBySourceAreaId(String sourceAreaId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_info WHERE source_area_id = ?")) {
            preparedStatement.setString(1, sourceAreaId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int autoId = rs.getInt("auto_id");
            String sourceId = rs.getString("source_id");
            int isUsed = rs.getInt("is_used");
            String pType2 = rs.getString("p_type_2");

            return new ChannelInfo(autoId, sourceId, sourceAreaId, isUsed, pType2);

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


    public List<ChannelInfo> findPageData(int amount, int page) {
        int offset = (page - 1) * amount;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_info LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            List<ChannelInfo> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String sourceId = rs.getString("source_id");
                String sourceAreaId = rs.getString("source_area_id");
                int isUsed = rs.getInt("is_used");
                String pType2 = rs.getString("p_type_2");
                ChannelInfo channelInfo = new ChannelInfo(autoId, sourceId, sourceAreaId, isUsed, pType2);
                list.add(channelInfo);
            }
            return list;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

}


