package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;

/*
 * @created 06/07/2022 - 12:12 PM
 * @project kafka-consumer
 * @author Prem
 */
@Repository
public class KafkaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertRecord(String text) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            preparedStatement = connection.prepareStatement("INSERT INTO store(`data`)VALUES('" + text + "');");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred , Cause :"
                    + e.toString());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
            DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
        }
    }

}
