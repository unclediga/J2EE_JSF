package ru.example.jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentDbUtil {
    private static StudentDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/student_tracker";

    public static StudentDbUtil getInstance() throws Exception {
        if (instance == null) {
            instance = new StudentDbUtil();
        }
        return instance;
    }

    private StudentDbUtil() throws Exception {
        dataSource = getDataSource();
    }

    private DataSource getDataSource() throws NamingException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndiName);
        return dataSource;
    }

    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("EX:" + e.getMessage());

        }
    }

    public List<Student> getStudents() throws SQLException {

        final List<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student");
            while (resultSet.next()) {
                final int id = resultSet.getInt("id");
                final String firstName = resultSet.getString("first_name");
                final String lastName = resultSet.getString("last_name");
                final String email = resultSet.getString("email");
                students.add(new Student(id, firstName, lastName, email));
            }
        } finally {
            close(connection, statement, resultSet);
        }
        return students;
    }

    public void addStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO student(last_name, first_name, email) VALUES(?, ?, ?)");
            statement.setString(1, student.getLastName());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getEmail());
            statement.execute();
        } finally {
            close(connection, statement);
        }
    }

    public Student getStudent(int id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Student student = null;
            if (resultSet.next()) {
                final String firstName = resultSet.getString("first_name");
                final String lastName = resultSet.getString("last_name");
                final String email = resultSet.getString("email");
                student = new Student(id, firstName, lastName, email);
            } else {
                throw new Exception("Can't find student id=" + id);
            }
            return student;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    public void updateStudent(Student student) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE student SET last_name=?, first_name=?, email=? WHERE id = ?");
            statement.setString(1, student.getLastName());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getEmail());
            statement.setInt(4, student.getId());
            statement.execute();
        } finally {
            close(connection, statement);
        }
    }

//    public void deleteStudent() {
//
//    }
}
