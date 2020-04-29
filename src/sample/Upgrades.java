package sample;


public class Upgrades {
    private int ID_Upgrade;
    private String name;
    private int Tool_ID;
    private int multiplier;
    private long price;
    private boolean status;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/
    public Upgrades(int ID_Upgrade, String name, int Tool_ID, int multiplier, long price, boolean status) {
        this.ID_Upgrade = ID_Upgrade;
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
        if (object.balance > price) {
            double balance = object.getBalance();
            object.setBalance(balance - price);
            activate();
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

    public int getID_Upgrade() {
        return ID_Upgrade;
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

    public void setID_Upgrade(int ID_Upgrade) {
        this.ID_Upgrade = ID_Upgrade;
    }

    public void setTool_ID(int tool_ID) {
        Tool_ID = tool_ID;
    }
}
