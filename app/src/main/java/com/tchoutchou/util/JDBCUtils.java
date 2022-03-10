package com.tchoutchou.util;

import android.app.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    public static Connection getConnection() throws NoConnectionException {
        Connection  connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysql-mathurin.alwaysdata.net:3306/mathurin_slcb"  // Lien vers la base de données
                    + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            String login = "mathurin_slcb";
            String pwd = "slcb+-*/+-*/";
            connection= DriverManager.getConnection(url,login,pwd);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        if (connection == null){
            throw new NoConnectionException();
        }
        return connection;
    }

    public static void close(Connection connection){
        try {
            assert connection != null;
            connection.close();
        } catch (SQLException |AssertionError e) {
            e.printStackTrace();
        }
    }

    public static String dateToSQLFormat(String date) {
        String[] tab = date.split("-");
        return tab[2]+'-'+tab[1]+'-'+tab[0];
    }

    public static boolean hasConnection(Activity activity){
        try {
            Connection connection = getConnection();
            if(connection == null)
                throw new NoConnectionException();
        } catch (NoConnectionException e) {
            return false;
        }
        return true;
    }

}
