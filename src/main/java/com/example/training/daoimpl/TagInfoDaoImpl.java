package com.example.training.daoimpl;

import com.example.training.bean.TagInfo;
import com.example.training.dao.TagInfoDao;
import com.example.training.util.JDBC;
import com.example.training.util.ResultSetToJson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TagInfoDaoImpl implements TagInfoDao {

    JDBC jdbc = new JDBC();

    @Override
    public void add(TagInfo tagInfo) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "INSERT INTO tag_info(tag_id, tag_name, type) " +
                    "VALUES (" + tagInfo.getTagId() + ",'" + tagInfo.getTagName() + "'," + tagInfo.getType() + ")";
            int rowsAffected = statement.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean delete(int tagId) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "DELETE FROM tag_info WHERE tag_id =" + tagId;
            int rowsAffected = statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int tagId, TagInfo tagInfo) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "UPDATE tag_info SET tag_name='" + tagInfo.getTagName() + "',type=" + tagInfo.getType() +
                    "WHERE tag_id=" + tagId;
            int rowsAffected = statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public String findByTagId(int tagId) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM tag_info WHERE tag_id=" + tagId;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "tag_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String findAll() {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM tag_info";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs, "tag_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
