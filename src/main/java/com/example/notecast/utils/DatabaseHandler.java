package com.example.notecast.utils;
import java.sql.*;

public class DatabaseHandler {
    public static boolean login(String user_email, String user_password)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
//            Connection connection = DriverManager.getConnection("jdbc:neo4j+s://a1264504.databases.neo4j.io:7687/notecastdbbeta",
//                    "neo4j", "9l54kNHf5dMxrTgswgUt3guVSJ_Mm3z9ad3tYEn4dw4");

            //TODO: change to PreparedStatement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from userinfo");
            while(resultSet.next())
            {
                String useremail = resultSet.getString("user_email");
                String password = resultSet.getString("user_password");

                if(useremail.equals(user_email))
                {
                    if(password.equals(user_password))
                    {
                        System.out.println("User email & password matched");
                        return true;
                    }
                }
            }
            System.out.println("User email & password don't match");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean signup(String user_email, String user_password)
    {
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
            Connection connection = DriverManager.getConnection("jdbc:neo4j+s://a1264504.databases.neo4j.io:7687/notecastdbbeta",
                    "neo4j", "9l54kNHf5dMxrTgswgUt3guVSJ_Mm3z9ad3tYEn4dw4");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from userinfo");
            while(resultSet.next())
            {
                String useremail = resultSet.getString("user_email");

                if(useremail.equals(user_email))
                {
                    System.out.println("User email already in use");
                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public static boolean delete()
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from userinfo");
            while(resultSet.next())
            {
                //
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
