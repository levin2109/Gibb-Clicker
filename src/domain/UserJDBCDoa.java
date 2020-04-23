package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDoa {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*---------------------------------------------
        Methods for Users (Login & Registration)
     --------------------------------------------*/
    //Get the user ID with the username
    public int getUserID(String username) {
        int ID_User = 0;
        String sql = "Select ID_User from User where Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            if (rs.next()) {
                ID_User = rs.getInt("ID_User");
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID_User;
    }

    //Login: check the password
    public boolean checkPassword(String name, String password) {
        List<String> allPassword = new ArrayList<String>();
        List<String> allUsername = new ArrayList<>();
        boolean correct = false;
        String sql = "Select Password, Username from User";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                allPassword.add(rs.getString("Password"));
                allUsername.add(rs.getString("Username"));
            }
            for (int i = 0; i < allPassword.size(); i++) {
                if (password.equals(allPassword.get(i)) && name.equals(allUsername.get(i))) {
                    correct = true;
                }
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return correct;
    }

    //check username
    public boolean checkUsername(String username) {
        boolean correct = true;
        List<String> all = new ArrayList<String>();
        String sql = "Select Username from User";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                all.add(rs.getString("Username"));
            }
            for (String Username : all) {
                if (username.equals(Username)) {
                    correct = false;
                }
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return correct;
    }

    //write registration in database
    public void registrationUser(String username, String password, long balance) {
        String sql = "Insert into User(Username, Password, Balance) Values (?,?,?)";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setLong(3,balance);
            ps.execute();
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
