package Util;

import Model.JdbcConfig;

import java.sql.*;

public class DBHelper {
    /*private static final String url="jdbc:mysql://localhost:3306/bbs?useUnicode=true&characterEncoding=UTF-8";
    private static final String name="com.mysql.jdbc.Driver";
    private static final String username="root";
    private static final String password="paul@outlook";*/
    private Connection connection;
    PreparedStatement preparedStatement;
    public DBHelper(String sql){
        JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
        try {
            Class.forName(jdbcConfig.getDriver());
            connection= DriverManager.getConnection(jdbcConfig.getUrl(),jdbcConfig.getUse(),jdbcConfig.getPassword());
            preparedStatement=connection.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet parseSQL(){
        ResultSet resultSet=null;
        try {
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void closeConnection(){
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int executeUpdate(){
        try {
            int i=preparedStatement.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }

}
