package com.example.notecast.utils;
import com.example.notecast.models.database.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    public static User login(String user_email, String user_password)
    {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
//            Connection connection = DriverManager.getConnection("jdbc:neo4j+s://a1264504.databases.neo4j.io:7687/notecastdbbeta",
//                    "neo4j", "9l54kNHf5dMxrTgswgUt3guVSJ_Mm3z9ad3tYEn4dw4");

            Statement statement = connection.createStatement();
            String query;
            PreparedStatement statement1;
            User user;

            query = "select * from userinfo where user_email = ?";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, user_email);
            ResultSet resultSet = statement1.executeQuery();

            String useremail = new String(), password = new String(), username = new String(), userprofession = new String(), userqosid = new String();
            boolean isFound = false;



            while(resultSet.next())
            {
                useremail = resultSet.getString("user_email");
                password = resultSet.getString("user_password");

                if(useremail.equals(user_email)) {
                    if (password.equals(user_password)) {
                        System.out.println("User email & password matched");
                        username = resultSet.getString("user_name");
                        userprofession = resultSet.getString("user_profession");
                        userqosid = resultSet.getString("qos_id");
                        isFound = true;
                        break;
                    }
                }
            }

            if(!isFound)
            {
                System.out.println("User email & password don't match");
                return null;
            }

            else
            {
                // add notebooks
                ArrayList<Notebook> notebooks = new ArrayList<>();

                query = "select * from notebookinfo where user_email = ?";
                statement1 = connection.prepareStatement(query);
                statement1.setString(1, user_email);
                ResultSet resultSetNotebook = statement1.executeQuery();


                while(resultSetNotebook.next())
                {
                    ArrayList<Topic> topics = new ArrayList<>();
                    String notebookid = resultSetNotebook.getString("notebook_id");
                    String notebooktitle = resultSetNotebook.getString("notebook_title");
                    int notebookpriority = resultSetNotebook.getInt("notebook_priority");
                    Timestamp notebooktimeCreated = resultSetNotebook.getTimestamp("notebook_date_created");
                    Timestamp notebooklastEdited = resultSetNotebook.getTimestamp("notebook_last_edit");

                    //add topics

                    query = "select * from topicinfo where notebook_id = ?";
                    statement1 = connection.prepareStatement(query);
                    statement1.setString(1, notebookid);
                    ResultSet resultSetTopic = statement1.executeQuery();



                    while(resultSetTopic.next())
                    {
                        String topicid = resultSetTopic.getString("topic_id");
                        String topic_title = resultSetTopic.getString("topic_title");
                        Timestamp topic_date_created = resultSetTopic.getTimestamp("topic_date_created");
                        Timestamp topic_last_edit = resultSetTopic.getTimestamp("topic_last_edit");
                        String content_id = resultSetTopic.getString("contents_id");

                        query = "select * from contentsinfo where contents_id = ?";
                        statement1 = connection.prepareStatement(query);
                        statement1.setString(1, content_id);
                        ResultSet resultSetContent = statement1.executeQuery();

                        Content content = null;

                        // add contents

                        while(resultSetContent.next())
                        {
                            String contentid = resultSetContent.getString("contents_id");
                            String baseStyles = resultSetContent.getString("contents_base_styles");
                            String basehtml = resultSetContent.getString("contents_base_html");
                            String basejs = resultSetContent.getString("contents_base_js");
                            String rootfolderlocation = resultSetContent.getString("contents_root_folder_location");

                            content = new Content(contentid, baseStyles, basehtml,basejs, rootfolderlocation);
                        }
                        topics.add(new Topic(topicid , topic_title , topic_date_created , topic_last_edit , notebookid, content));
                    }

                    notebooks.add(new Notebook(notebookid, notebooktitle, notebookpriority, notebooktimeCreated , notebooklastEdited , useremail, topics));
                }

                // add qos

                query = "select * from quality_of_services where qos_id = ?";
                statement1 = connection.prepareStatement(query);
                statement1.setString(1, userqosid);
                ResultSet resultSetQos = statement1.executeQuery();

                QualityOfService qos = null;

                while(resultSetQos.next())
                {
                    String qos_id = resultSetQos.getString("qos_id");
                    String qos_service_type = resultSetQos.getString("qos_service_type");
                    qos = new QualityOfService(qos_id, qos_service_type);
                }

                user = new User(username , useremail, user_password, userprofession , qos, notebooks);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static User signup(String user_name, String user_email, String user_password , String user_profession)
    {
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
            Connection connection = DriverManager.getConnection("jdbc:neo4j+s://a1264504.databases.neo4j.io:7687/notecastdbbeta",
                    "neo4j", "9l54kNHf5dMxrTgswgUt3guVSJ_Mm3z9ad3tYEn4dw4");

            Statement statement = connection.createStatement();

            User user;
            String query;
            PreparedStatement statement1;

            query = "select * from userinfo where user_email = ?";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, user_email);
            ResultSet resultSet = statement1.executeQuery();

            if(resultSet.next())
            {
                System.out.println("User email already in use");
                return null;
            }

            // creating content
            Content content;
            content = new Content(null, null, null, null, null);
            // saving into db

            query = "insert into contentsinfo (contents_id,contents_base_html,contents_base_styles,contents_base_js,contents_root_folder_location) values (?, ?, ?, ?, ?)";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, content.getId());
            statement1.setString(2, content.getBaseHTML());
            statement1.setString(3, content.getBaseStyles());
            statement1.setString(4, content.getBaseJS());
            statement1.setString(5, content.getRootLocation());
            statement1.executeUpdate();

            // creating notebooks
            Notebook notebook;
            ArrayList<Topic> topics = new ArrayList<>();

            notebook = new Notebook(null,  null,  -1, null, null, user_email, topics);

            query = "insert into notebookinfo (notebook_id , notebook_title, notebook_priority, notebook_date_created, notebook_last_edit, user_email) values(? ,? ,? ,? ,? ,?)";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, notebook.getId());
            statement1.setString(2, notebook.getTitle());
            statement1.setInt(3, notebook.getPriority());
            statement1.setTimestamp(4, notebook.getTimeCreated());
            statement1.setTimestamp(6, notebook.getLastEdited());
            statement1.setString(3, user_email);
            statement1.executeUpdate();


            // creating topic
            Topic topic;
            topic = new Topic(null, null, null, null , null, content);

            //saving into db

            query = "insert into topicinfo (topic_id, topic_title, topic_date_created, topic_last_edit, notebook_id, contents_id) values(?, ? ,? , ? , ? ,?)";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, topic.getId());
            statement1.setString(2, topic.getTitle());
            statement1.setTimestamp(3, topic.getTimeCreated());
            statement1.setTimestamp(4, topic.getLastEdited());
            statement1.setString(5, notebook.getId());
            statement1.setString(6, content.getId());
            statement1.executeUpdate();

            notebook.addTopic(topic);

            // creating qos
            QualityOfService qualityOfService;
            qualityOfService = new QualityOfService(null, null);

            query = "insert into quality_of_services (qos_id, qos_service_type) values(? , ?)";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, qualityOfService.getId());
            statement1.setString(2, qualityOfService.getType());
            statement1.executeUpdate();

            // creating user
            ArrayList<Notebook> notebooks = new ArrayList<>();
            notebooks.add(notebook);

            user = new User(user_name, user_email, user_password , user_profession, qualityOfService, notebooks);
            // writing in database

            query = "insert into userinfo (user_name ,user_email, user_password, user_profession, qos_id) values (? , ? , ? , ? , ?)";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, user.getName());
            statement1.setString(2, user.getEmail());
            statement1.setString(3, user.getPassword());
            statement1.setString(4, user.getProfession());
            statement1.setString(5, qualityOfService.getId());
            statement1.executeUpdate();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean delete()
    {
        // return user object
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
            Statement statement = connection.createStatement();

            String query;
            PreparedStatement statement1;




            String useremail = new String(), password = new String(),username = new String(), userprofession = new String(), userqosid = new String();
            boolean isFound = false;
            User user;

            query = "select * from userinfo where user_email = ?";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, useremail);
            ResultSet resultSet = statement1.executeQuery();

            while(resultSet.next())
            {
                //
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean save(User user)
    {
        // write from user object to database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/notecastdbbeta", "root", "8664");
            Statement statement = connection.createStatement();

            String query;
            PreparedStatement statement1;
            ResultSet resultSet;

            // updating user table
            // changing user email is not implemented
            query = "update userinfo set user_name = ?, user_password = ?, user_profession = ? where user_email = ?";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, user.getName());
            statement1.setString(2, user.getPassword());
            statement1.setString(3, user.getProfession());
            statement1.setString(4, user.getEmail());
            statement1.executeUpdate();

            // updating qos table

            query = "update quality_of_services set qos_service_type = ? where qos_id = ?";
            statement1 = connection.prepareStatement(query);
            statement1.setString(1, user.getQos().getType());
            statement1.setString(2, user.getQos().getId());
            statement1.executeUpdate();

            // updating notebooks
            // using loops to iterate notebooks one by one

            for(var notebook :user.getNotebooks())
            {
                // updating topic
                for(var topic : notebook.getTopics())
                {
                    // updating content
                    var content = topic.getContent();
                    query = "update contentsinfo set contents_base_html = ? , contents_base_styles = ?  ,contents_base_js = ?,contents_root_folder_location = ?   where contents_id = ?";
                    statement1 = connection.prepareStatement(query);
                    statement1.setString(1, content.getBaseHTML());
                    statement1.setString(2, content.getBaseStyles());
                    statement1.setString(3, content.getBaseJS());
                    statement1.setString(4, content.getRootLocation());
                    statement1.setString(5, content.getId());
                    statement1.executeUpdate();

                    // updating topic

                    query = "update topicinfo set topic_title = ? , topic_last_edit = ?  where topic_id = ?";
                    statement1 = connection.prepareStatement(query);
                    statement1.setString(1, topic.getTitle());
                    statement1.setTimestamp(2, topic.getLastEdited());
                    statement1.setString(3, topic.getId());
                    statement1.executeUpdate();
                }

                // updating notebook

                query = "update notebookinfo set notebook_title = ? , notebook_priority = ?, notebook_last_edit = ?   where notebook_id = ?";
                statement1 = connection.prepareStatement(query);
                statement1.setString(1, notebook.getTitle());
                statement1.setInt(2, notebook.getPriority());
                statement1.setTimestamp(3, notebook.getLastEdited());
                statement1.setString(4, notebook.getId());
                statement1.executeUpdate();

            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
