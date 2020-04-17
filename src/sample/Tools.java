package sample;

import java.util.List;

public class Tools {
    private String name;
    private long price;
    private int level;
    private boolean status;
    private long moneyPerSecond;
    private int multiplier;



    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Tools(String name, long price, int level, boolean status, long moneyPerSecond, int multiplier) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.status = status;
        this.moneyPerSecond = moneyPerSecond;
        this.multiplier = multiplier;
    }
    /*------------------------------------------------
                         Methods
    ------------------------------------------------*/
    public void activate() {
        status = true;
    }

    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public int getMultiplier() {
        int Tool_ID = getToolID();
        List<Upgrades> = getUpgradesWithToolID();
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
    /*------------------------------------------------
                          Setter
    ------------------------------------------------*/
    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public void setMoneyPerSecond(long moneyPerSecond) {
        this.moneyPerSecond = moneyPerSecond;
    }

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
