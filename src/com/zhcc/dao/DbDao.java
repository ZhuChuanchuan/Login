package com.zhcc.dao;

import java.sql.*;

/**
 * Create by ZHCC on 2018/9/14
 */
public class DbDao {
    private Connection conn;
    private String driver;
    private String url;
    private String username;
    private String password;
    public DbDao(){}

    public DbDao(String dirver, String url, String usernmae, String password) {
        this.driver=dirver;
        this.url=url;
        this.username=usernmae;
        this.password=password;
    }

    public Connection getConn() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url, this.username, this.password);
        }
        return conn;
    }

    //插入
    public boolean insert(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = getConn().prepareStatement(sql);
        for(int i=0;i<args.length;i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        if (pstmt.executeUpdate() != 1) {
            return false;
        }
        return true;
    }
    //查询
    public ResultSet query(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = getConn().prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt.executeQuery();
    }

    //执行修改
    public void modify(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = getConn().prepareStatement(sql);
        for(int i=0;i<args.length;i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        pstmt.executeUpdate();
        pstmt.close();
    }
    public void closeConn() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
