package sample;


import domain.ToolsJDBCDoa;

import java.util.List;

public class Upgrades {
    private int idUpgrade;
    private String name;
    private int toolId;
    private int multiplier;
    private long price;
    private boolean status;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Upgrades(int idUpgrade, String name, int toolId, int multiplier, long price, boolean status) {
        this.idUpgrade = idUpgrade;
        this.name = name;
        this.toolId = toolId;
        this.multiplier = multiplier;
        this.price = price;
        this.status = status;
    }

    /*------------------------------------------------
                        Methods
    ------------------------------------------------*/
    public void activate() {
        status = true;
    }

    public void buy(Gibb game, List<Tools> toolsList) {
        ToolsJDBCDoa Tools = new ToolsJDBCDoa();
        long balance = game.getBalance();
        if (balance > price) {
            game.setBalance(balance - price);
            activate();
            String toolname = Tools.getToolName(this.toolId);
            for (Tools tool : toolsList) {
                if (toolname.equals(tool.getName())) {
                    if (tool.getMultiplier() == 1) {
                        tool.setMultiplier(this.multiplier);
                    } else {
                        tool.setMultiplier(tool.getMultiplier()+this.multiplier);
                    }
                    tool.updateMoneyPerSecond();
                }
            }
        }
    }

    /*------------------------------------------------
                             Getter
    ------------------------------------------------*/
    public boolean isStatus() {
        return status;
    }

    public long getPrice() {
        return price;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getTool_ID() {
        return toolId;
    }

    public String getName() {
        return name;
    }

    public int getID_Upgrade() {
        return idUpgrade;
    }

    /*------------------------------------------------
                             Setter
        ------------------------------------------------*/
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setObject(int Tool_ID) {
        this.toolId = Tool_ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID_Upgrade(int id_upgrade) {
        this.idUpgrade = id_upgrade;
    }

    public void setTool_ID(int tool_ID) {
        toolId = tool_ID;
    }
}
