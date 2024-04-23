package com.example.training.daoimpl;

import com.example.training.bean.PType2Info;
import com.example.training.dao.PType2InfoDao;
import com.example.training.util.JDBC;
import com.example.training.util.ResultSetToJson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PType2InfoDaoImpl implements PType2InfoDao {

    JDBC jdbc = new JDBC();

    @Override
    public void add(PType2Info pType2Info) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "INSERT INTO p_type_2_info(category, name) " +
                    "VALUES ('" + pType2Info.getCategory() + "','" + pType2Info.getName() + "')";
            int rowsAffected = statement.executeUpdate(insertSQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "DELETE FROM p_type_2_info WHERE auto_id =" + id;
            int rowsAffected = statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, PType2Info pType2Info) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "UPDATE p_type_2_info SET category='" + pType2Info.getCategory() + "',name='" + pType2Info.getName() +
                    "' WHERE auto_id=" + id;
            int rowsAffected = statement.executeUpdate(insertSQL);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public String findById(int id) {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM p_type_2_info WHERE auto_id=" + id;
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs,"p_type_2_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String findAll() {
        try (Connection conn = jdbc.getConnection();
             Statement statement = conn.createStatement()) {
            String insertSQL = "SELECT * FROM p_type_2_info";
            ResultSet rs = statement.executeQuery(insertSQL);
            return ResultSetToJson.ResultSetToJsonString(rs,"p_type_2_info");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
