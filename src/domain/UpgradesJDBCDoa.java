package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpgradesJDBCDoa {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*---------------------------------------------
        Methods for Users (Login & Registration)
     --------------------------------------------*/
    //registrate upgrades
    public void registrateUpgrades(int User_ID, int Upgrades_ID, boolean status) {
        String sql = "Insert into User_Upgrades(User_ID, Tools_ID, Status) Values (?,?,?)";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, User_ID);
            ps.setInt(2, Upgrades_ID);
            ps.setBoolean(3, status);
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