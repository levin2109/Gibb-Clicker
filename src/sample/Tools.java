package sample;

import domain.ToolsJDBCDoa;

public class Tools {
    private String name;
    private long price;
    private int level;
    private boolean status;
    private long moneyPerSecond;
    private int multiplier = 1;
    private long pricePerLevel;
    ToolsJDBCDoa Tools = new ToolsJDBCDoa();


    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Tools(String name, long price, int level, boolean status, long moneyPerSecond, int multiplier, long pricePerLevel) {
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

    //buy this Tool
    public void buy(Gibb game) {
        long balance = game.getBalance();
        if (level != 0) {
            if (balance >= (price + (pricePerLevel * level))) {
                game.setBalance(balance - (price + (pricePerLevel * level)));
                level = level + 1;
                moneyPerSecond = moneyPerSecond * level;
            }
        } else {
            if (balance >= price) {
                game.setBalance(balance - price);
                multiplier = 1;
                activate();
                level = level + 1;
                moneyPerSecond = moneyPerSecond * multiplier;
            }
        }
    }

    //update MoneyPerSecond
    public void updateMoneyPerSecond() {
        System.out.println(moneyPerSecond);
        System.out.println(multiplier);
        moneyPerSecond = moneyPerSecond * multiplier;
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

    //get the Tool ID with the toolname
    public int getToolID() {
        return Tools.getToolID(this.name);
    }

    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public int getMultiplier() {
        return multiplier;
    }

    public long getMoneyPerSecond() {
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

    public long getPricePerLevel() {
        return pricePerLevel;
    }

    public ToolsJDBCDoa getTools() {
        return Tools;
    }

    /*------------------------------------------------
                                  Setter
            ------------------------------------------------*/
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setMoneyPerSecond(long moneyPerSecond) { this.moneyPerSecond = moneyPerSecond; }

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

    public void setPricePerLevel(long pricePerLevel) {
        this.pricePerLevel = pricePerLevel;
    }

    public void setTools(ToolsJDBCDoa tools) {
        Tools = tools;
    }
}
