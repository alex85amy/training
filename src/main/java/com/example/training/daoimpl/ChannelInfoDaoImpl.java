package com.example.training.daoimpl;

import com.example.training.bean.ChannelInfo;
import com.example.training.dao.ChannelInfoDao;
import com.example.training.util.ResultSetToJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class ChannelInfoDaoImpl implements ChannelInfoDao {


    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public ChannelInfoDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(ChannelInfo channelInfo) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "INSERT INTO channel_info(source_id, source_area_id, is_used, p_type_2) " +
                    "VALUES ('" + channelInfo.getSourceId() + "','" + channelInfo.getSourceAreaId() + "'," + channelInfo.getIsUsed() + ",'" + channelInfo.getPType2() + "')";
            statement.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
        }
    }

    @Override
    public boolean delete(int id) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "DELETE FROM channel_info WHERE auto_id = " + id;
            statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables);
            return false;
        }
    }

    @Override
    public boolean update(int id, ChannelInfo channelInfo) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "UPDATE channel_info SET source_id='" + channelInfo.getSourceId() + "',source_area_id='" + channelInfo.getSourceAreaId() + "',is_used=" + channelInfo.getIsUsed() + ",p_type_2='" + channelInfo.getPType2() +
                    "' WHERE auto_id=" + id;
            statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }

    @Override
    public String findById(int id) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_info WHERE auto_id=" + id;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findAll() {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_info";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
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

    @Override
    public String findpagedata(int per_page, int page) {
        int offset = (page - 1) * per_page;
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_info LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}


