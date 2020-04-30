package domain;

import sample.Tools;
import sample.Upgrades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SaveJDBCDoa {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*-----------------------------------------
                Methods to save
    -----------------------------------------*/
    //save all new tool levels
    public void saveTools(List<Tools> toolsList, int User_ID) {
        String sql = "Update User_Tools set Level = ?, Status = ?, Price = ? where User_ID = ? and Tools_ID = ?";
        try {
            con = openConnection();
            for (Tools tool : toolsList) {
                long price = (long) (tool.getPrice());
                ps = con.prepareStatement(sql);
                ps.setInt(1, tool.getLevel());
                ps.setBoolean(2,tool.isStatus());
                ps.setLong(3, price);
                ps.setInt(4, User_ID);
                ps.setInt(5, tool.getToolID());
                ps.execute();
            }
            closeConnection();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //save all all new upgrade levels
    public void saveUpgrades(List<Upgrades> upgradesList, int User_ID) {
        String sql = "Update User_Upgrades set Status = ? where User_ID = ? and Upgrades_ID = ?";
        try {
            con = openConnection();
            for (Upgrades upgrade : upgradesList) {
                ps = con.prepareStatement(sql);
                ps.setBoolean(1, upgrade.isStatus());
                ps.setInt(2, User_ID);
                ps.setInt(3, upgrade.getID_Upgrade());
                ps.execute();
            }
            closeConnection();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //save the balance
    public void saveBalance(double balance, int User_ID) {
        String sql = "Update User set Balance = ? where ID_User = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setInt(2, User_ID);
            ps.execute();
            closeConnection();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*-----------------------------------------
          Verbindungs auf-und abbau
    -----------------------------------------*/
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
