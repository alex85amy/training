package com.example.training.dao;

import com.example.training.bean.ChannelTagMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChannelTagMappingDao {

    private final Connection conn;
    private final Logger logger = LogManager.getLogger();

    public ChannelTagMappingDao(Connection conn) {
        this.conn = conn;
    }


    public void add(ChannelTagMapping channelTagMapping) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO channel_tag_mapping(s_area_id, tag_id) VALUES (?, ?)")) {
            preparedStatement.setString(1, channelTagMapping.getSourceAreaId());
            preparedStatement.setInt(2, channelTagMapping.getTagId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }


    public boolean delete(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "DELETE FROM channel_tag_mapping WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
    }


    public boolean update(int id, ChannelTagMapping channelTagMapping) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "UPDATE channel_tag_mapping SET s_area_id = ?,tag_id= ? WHERE auto_id = ?")) {
            preparedStatement.setString(1, channelTagMapping.getSourceAreaId());
            preparedStatement.setInt(2, channelTagMapping.getTagId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return false;
        }
    }


    public ChannelTagMapping findById(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_tag_mapping WHERE auto_id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String sourceAreaId = rs.getString("s_area_id");
            int tagId = rs.getInt("tag_id");

            return new ChannelTagMapping(id, sourceAreaId, tagId);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


    public List<ChannelTagMapping> findBySourceAreaId(String sourceAreaId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_tag_mapping WHERE s_area_id = ?")) {
            preparedStatement.setString(1, sourceAreaId);
            ResultSet rs = preparedStatement.executeQuery();
            List<ChannelTagMapping> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                int tagId = rs.getInt("tag_id");
                ChannelTagMapping channelTagMapping = new ChannelTagMapping(autoId, sourceAreaId, tagId);
                list.add(channelTagMapping);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


    public List<ChannelTagMapping> findByTagId(int tagId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_tag_mapping WHERE tag_id = ?")) {
            preparedStatement.setInt(1, tagId);
            ResultSet rs = preparedStatement.executeQuery();
            List<ChannelTagMapping> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String sourceAreaId = rs.getString("s_area_id");
                ChannelTagMapping channelTagMapping = new ChannelTagMapping(autoId, sourceAreaId, tagId);
                list.add(channelTagMapping);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


    public ChannelTagMapping findBySIdAndTagId(String sourceAreaId, int tagId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_tag_mapping WHERE s_area_id = ? AND　tag_id = ?")) {
            preparedStatement.setString(1, sourceAreaId);
            preparedStatement.setInt(2, tagId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int autoId = rs.getInt("auto_id");

            return new ChannelTagMapping(autoId, sourceAreaId, tagId);

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


    public List<ChannelTagMapping> findAll() {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_tag_mapping")) {
            ResultSet rs = preparedStatement.executeQuery();
            List<ChannelTagMapping> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String sourceAreaId = rs.getString("s_area_id");
                int tagId = rs.getInt("tag_id");
                ChannelTagMapping channelTagMapping = new ChannelTagMapping(autoId, sourceAreaId, tagId);
                list.add(channelTagMapping);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


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

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());

        }
    }


    public List<ChannelTagMapping> findPageData(int amount, int page) {
        int offset = (page - 1) * amount;
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM channel_tag_mapping LIMIT ? OFFSET ?")) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            List<ChannelTagMapping> list = new ArrayList<>();
            while (rs.next()) {
                int autoId = rs.getInt("auto_id");
                String sourceAreaId = rs.getString("s_area_id");
                int tagId = rs.getInt("tag_id");
                ChannelTagMapping channelTagMapping = new ChannelTagMapping(autoId, sourceAreaId, tagId);
                list.add(channelTagMapping);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }


}
