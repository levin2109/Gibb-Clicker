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
    //Get the tool ID with the toolname
    public int getToolID(String name) {
        int ID_Tools = 0;
        String sql = "Select ID_Tools from Tools where name = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            ID_Tools = rs.getInt("ID_Tools");
            closeConnection();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID_Tools;
    }

    //create all tools from database
    public List<Tools> loadTools() {
        List<Tools> all = new ArrayList<>();

        String sql = "Select * from Tools";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID_Tools");
                all.add(new Tools(rs.getString("Name"), rs.getLong("Price"), 0, false,rs.getLong("MoneyPerSecond"), 0, rs.getLong("PricePerLevel")));
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
        String sql = "Select MoneyPerSecond, Level from Tools join User_Tools on ID_Tools=Tools_ID where status = true && ID_Tools = ? && Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID_Tools);
            ps.setString(2,username);
            rs = ps.executeQuery();
            Long moneyPerSecond = rs.getLong("MoneyPerSecond");
            int level = rs.getInt("Level");
            money = moneyPerSecond * level;
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
        String sql = "Select Multiplier from Upgrades join User_Upgrades on ID_Tools=Tools_ID where Status = true && ID_Tools = ? && Username = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID_Tools);
            ps.setString(2,username);
            rs = ps.executeQuery();
            multiplier = rs.getInt("Multiplier");
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
        String sql = "Insert into User(User_ID, Tools_ID, Level, Status) Values (?,?,?,?)";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,User_ID);
            ps.setInt(2,Tools_ID);
            ps.setInt(3,level);
            ps.setBoolean(4,status);
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
