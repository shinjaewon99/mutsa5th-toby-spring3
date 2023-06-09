package com.example.likelion_spring.dao;

import com.example.likelion_spring.domain.User;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // 호출하여 사용
        Connection c = connectionMaker.makeConnection();

        // 값을 insert(추가)하는 쿼리문 작성
        PreparedStatement ps = c.prepareStatement
                ("insert into users(id, name, password) values(?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();

        // 값을 뽑아오는 쿼리문 작성
        PreparedStatement ps = c.prepareStatement
                ("select * from users where id = ?");

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
