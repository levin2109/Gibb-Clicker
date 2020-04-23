package sample;

public class Upgrades {
    private String name;
    private int Tool_ID;
    private int multiplier;
    private long price;
    private boolean status;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Upgrades(String name, int Tool_ID, int multiplier, long price, boolean status) {
        this.name = name;
        this.Tool_ID = Tool_ID;
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

    public void buy(Gibb object) {
        if (object.money > price) {
            long balance = object.getMoney();
            object.setMoney(balance - price);
        } else {
            System.out.println("Du hast zu wenig Geld!");
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
        return Tool_ID;
    }

    public String getName() {
        return name;
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
        this.Tool_ID = Tool_ID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
