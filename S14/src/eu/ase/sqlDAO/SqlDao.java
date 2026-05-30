package eu.ase.sqlDAO;

import java.io.File;
import java.sql.*;


public class SqlDao {
    private Connection sqlLiteConn;
    private static SqlDao currentInstance;

    private SqlDao() throws ClassNotFoundException, SQLException {
        boolean cdb = false;
        File f = new File("./users.db");
        if (f.exists()) {
            cdb = true;
        }
        Class.forName("org.sqllite.JDBC");
        sqlLiteConn = DriverManager.getConnection("jdbc:sqlite:users.db");
        if (cdb) {
            createDBTable();
        }
    }

    private void createDBTable() throws SQLException {
        Statement stmt = sqlLiteConn.createStatement();
        String sqlCreateTable = "create table USERS (ID INT PRIMARY KEY NOT NULL," + " NAME TEXT NOT NULL," + " EMAIL CHAR(50), PASSWORD TEXT NOT NULL)";
        stmt.executeUpdate(sqlCreateTable);

    }

    public void InsertIntoDBTable(int id, String name, String email, String password) throws SQLException, InterruptedException {
        Thread.sleep(10000);
        if (sqlLiteConn != null) {
            PreparedStatement stmt = sqlLiteConn.prepareStatement("insert into USERS(ID,NAME,EMAIL,PASSWORD) values (?,?,?,?) ");
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.executeUpdate();
            stmt.close();
            sqlLiteConn.commit();
        }
    }

    public void DisplayDB() throws SQLException {
        System.out.println("Display DB: ");
        Statement stmt = sqlLiteConn.createStatement();
        String sqlSelect = "select * from USERS";
        ResultSet rs = stmt.executeQuery(sqlSelect);
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String email = rs.getString("EMAIL");
            String password = rs.getString("PASSWORD");
            System.out.println("ID: " + id + "NAME: " + name + " EMAIL: " + email + " PASSWORD: " + password);
        }
        rs.close();
        stmt.close();
    }

    public static synchronized SqlDao getInstance() throws ClassNotFoundException, SQLException {
        if (currentInstance == null) {
            currentInstance = new SqlDao();
        }
        return currentInstance;
    }

    public void closeDB() {
        if (sqlLiteConn != null) {
            try {
                sqlLiteConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
