package domain;

import sample.Upgrades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpgradesJDBCDoa {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*---------------------------------------------
        Methods for Users (Login & Registration)
     --------------------------------------------*/
    //create all upgrade-objects from database
    public List<Upgrades> loadUpgrades(String username) {
        List<Upgrades> all = new ArrayList<>();

        String sql = "Select ID_Upgrades, Name, Upgrades.Tools_ID, Multiplier, Price, Status from Upgrades join User_Upgrades on ID_Upgrades=Upgrades_ID join User on ID_User=User_ID where username = ? order by price asc";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                all.add(new Upgrades(rs.getInt("ID_Upgrades"), rs.getString("Name"), rs.getInt("Tools_ID"), rs.getInt("Multiplier"), rs.getLong("Price"), rs.getBoolean("Status")));
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    //registrate upgrades
    public void registrateUpgrades(int User_ID, int Upgrades_ID, boolean status) {
        String sql = "Insert into User_Upgrades(User_ID, Upgrades_ID, Status) Values (?,?,?)";
        try {
            setForeignKeyChecks0();
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, User_ID);
            ps.setInt(2, Upgrades_ID);
            ps.setBoolean(3, status);
            ps.execute();
            closeConnection();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //set foreign keys checks 0
    public void setForeignKeyChecks0() {
        String sql = "Set foreign_key_checks = 0";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.execute();
            closeConnection();
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