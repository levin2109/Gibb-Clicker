package sample;

public class Upgrades {
    private String name;
    private String object;
    private int multiplier;
    private long price;
    private boolean status;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Upgrades(String name, String object, int multiplier, long price, boolean status) {
        this.name = name;
        this.object = object;
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

    public String getObject() {
        return object;
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

    public void setObject(String object) {
        this.object = object;
    }

    public void setName(String name) {
        this.name = name;
    }
}
