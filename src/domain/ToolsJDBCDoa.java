package domain;

import sample.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolsJDBCDoa {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /*----------------------------------
            methods for Tools
     ---------------------------------*/
    //Get the toolname with the tool id
    public String getToolName(int ID_Tool) {
        String toolname = "";
        String sql = "Select Name from Tools where ID_Tools = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID_Tool);
            rs = ps.executeQuery();
            if (rs.next()) {
                toolname = rs.getString("Name");
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toolname;
    }

    //Get the tool ID with the toolname
    public int getToolID(String name) {
        int ID_Tools = 0;
        String sql = "Select ID_Tools from Tools where name = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            if (rs.next()) {
                ID_Tools = rs.getInt("ID_Tools");
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID_Tools;
    }

    //create all tool-objects from database
    public List<Tools> loadTools(String username) {
        List<Tools> all = new ArrayList<>();
        long moneyPerSecond;
        String sql = "Select Name, Price, Level, Status, MoneyPerSecond, PricePerLevel from Tools join User_Tools on ID_Tools=Tools_ID join User on ID_User=User_ID where Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("Level") != 0) {
                    moneyPerSecond = rs.getInt("Level") * rs.getLong("MoneyPerSecond");
                } else {
                    moneyPerSecond = rs.getLong("MoneyPerSecond");
                }
                all.add(new Tools(rs.getString("Name"), rs.getLong("Price"), rs.getInt("Level"), rs.getBoolean("Status"), moneyPerSecond, 0, rs.getLong("PricePerLevel")));
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    //calculate the moneyPerSecond for a tool
    public long calcMoneyPerSecond(int ID_Tools, String username) {
        long money = 0;
        long moneyPerSecond = 1;
        String sql = "Select MoneyPerSecond, Level from Tools join User_Tools on ID_Tools=Tools_ID join User on ID_User=User_ID where status = true && ID_Tools = ? && Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID_Tools);
            ps.setString(2,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                moneyPerSecond = rs.getInt("MoneyPerSecond");
                int level = rs.getInt("Level");
                money = moneyPerSecond * level;
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }

    //calculate the multiplier for a tool
    public int calcMultiplier(int ID_Tools, String username) {
        int multiplier = 0;
        int newMultiplier = 0;
        String sql = "Select Multiplier from Tools join Upgrades on ID_Tools=Tools_ID join User_Upgrades on ID_Upgrades=Upgrades_ID join User on ID_User=User_ID where Status = true && ID_Tools = ? && Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID_Tools);
            ps.setString(2,username);
            rs = ps.executeQuery();
            while (rs.next()) {
                newMultiplier = rs.getInt("Multiplier");
                multiplier = multiplier + newMultiplier;
            }
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return multiplier;
    }

    //registrate tools
    public void registrateTools(int User_ID, int Tools_ID, int level, boolean status) {
        String sql = "Insert into User_Tools(User_ID, Tools_ID, Level, Status) Values (?,?,?,?)";
        try {
            setForeignKeyChecks0();
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,User_ID);
            ps.setInt(2,Tools_ID);
            ps.setInt(3,level);
            ps.setBoolean(4,status);
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
