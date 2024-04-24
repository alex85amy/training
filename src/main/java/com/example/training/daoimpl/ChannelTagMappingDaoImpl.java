package com.example.training.daoimpl;

import com.example.training.bean.ChannelTagMapping;
import com.example.training.dao.ChannelTagMappingDao;
import com.example.training.util.ResultSetToJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class ChannelTagMappingDaoImpl implements ChannelTagMappingDao {

    private Connection conn;
    private Logger logger = LogManager.getLogger();

    public ChannelTagMappingDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(ChannelTagMapping channelTagMapping) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "INSERT INTO channel_tag_mapping(s_area_id, tag_id) " +
                    "VALUES ('" + channelTagMapping.getSourceAreaId() + "'," + channelTagMapping.getTagId() + ")";
            statement.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
        }
    }

    @Override
    public boolean delete(int id) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "DELETE FROM channel_tag_mapping WHERE auto_id =" + id;
            statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return false;
        }
    }

    @Override
    public boolean update(int id, ChannelTagMapping channelTagMapping) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "UPDATE channel_tag_mapping SET s_area_id='" + channelTagMapping.getSourceAreaId() + "',tag_id=" + channelTagMapping.getTagId() +
                    "WHERE auto_id=" + id;
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
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE auto_id =" + id;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findBySourceAreaId(String sourceAreaId) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE s_area_id ='" + sourceAreaId + "'";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findByTagId(int tagId) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE tag_id =" + tagId;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findBySIdAndTagId(String sourceAreaId, int tagId) {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping WHERE s_area_id ='" + sourceAreaId + "'AND　tag_id =" + tagId;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public String findAll() {
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }

    @Override
    public void addBatch(List<ChannelTagMapping> channelTagMappingList) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
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
            logger.error(throwables.toString());

        }
    }

    @Override
    public String findpagedata(int per_page, int page) {
        int offset = (page - 1) * per_page;
        try (Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM channel_tag_mapping LIMIT " + per_page + " OFFSET " + offset;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "channel_tag_mapping");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.toString());
            return null;
        }
    }


}
