package sample;

import domain.ToolsJDBCDoa;

public class Tools {
    private String name;
    private long price;
    private int level;
    private boolean status;
    private double moneyPerSecond;
    private int multiplier = 1;
    private long pricePerLevel;
    ToolsJDBCDoa Tools = new ToolsJDBCDoa();


    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Tools(String name, long price, int level, boolean status, double moneyPerSecond, int multiplier, long pricePerLevel) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.status = status;
        this.moneyPerSecond = moneyPerSecond;
        this.multiplier = multiplier;
        this.pricePerLevel = pricePerLevel;
    }
    /*------------------------------------------------
                         Methods
    ------------------------------------------------*/
    //Change the Tool to true
    public void activate() {
        status = true;
    }

    //load the Money per Second for the Player when he is back in the game
    public void loadMoneyPerSecond(String username) {
        int ID_Tools = Tools.getToolID(this.name);
        this.moneyPerSecond = Tools.calcMoneyPerSecond(ID_Tools, username) * multiplier;
    }

    //load the Multiplier for the Player when he is back in the game
    public void loadMultiplier(String username) {
        int ID_Tools = Tools.getToolID(this.name);
        if (Tools.calcMultiplier(ID_Tools, username) != 0) {
            this.multiplier = Tools.calcMultiplier(ID_Tools, username);
        } else {
            multiplier = 1;
        }
    }

    public int getToolID() {
        return Tools.getToolID(this.name);
    }

    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public int getMultiplier() {
        return multiplier;
    }

    public double getMoneyPerSecond() {
        return moneyPerSecond;
    }

    public boolean isStatus() {
        return status;
    }

    public int getLevel() {
        return level;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    /*------------------------------------------------
                          Setter
    ------------------------------------------------*/
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setMoneyPerSecond(double moneyPerSecond) { this.moneyPerSecond = moneyPerSecond; }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
