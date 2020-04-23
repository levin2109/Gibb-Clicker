package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCDoa {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*---------------------------------------------
        Methods for Users (Login & Registration)
     --------------------------------------------*/
    //Login: check the password
    public boolean checkPassword(String name, String password) {
        String password_db = "";
        boolean correct = false;
        String sql = "Select Password from User where Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            password_db = rs.getString("Password");
            closeConnection();
            if (password == password_db) {
                correct = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return correct;
    }

    //check username
    public boolean checkUsername(String username) {
        String username_db = "";
        boolean correct = false;
        String sql = "Select Username from User where Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            username_db = rs.getString("Username");
            closeConnection();
            if (username != username_db) {
                correct = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return correct;
    }

    //write registration in database
    public void registration(String username, String password, long balance) {
        String sql = "Insert into User(Username, Password, Balance) Values (?,?,?)";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setLong(3,balance);
            rs = ps.executeQuery();
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*-----------------------------------------
              Verbindungs auf-und abbau
     ----------------------------------------*/
    private Connection openConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    private void closeConnection() {
        try {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error in " + getClass().getName() + ": "
                    + e.getMessage());
        }
    }
}
