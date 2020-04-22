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

    public long calcMoneyPerSecond(int ID_Tools) {
        long money = 0;
        String sql = "Select MoneyPerSecond, Level from Tools where active = true && ID_Tools = ?";
        try {
            con = openConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,ID_Tools);
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
