package sample;

public class Gibb {
    public static long money;
    private long moneyPerClick;
    private int multiplier;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/

    public Gibb(long money, long moneyPerClick) {
        this.money = money;
        this.moneyPerClick = moneyPerClick;
    }

    /*------------------------------------------------
                         Methods
    ------------------------------------------------*/
    public void click() {
        money = money + (moneyPerClick*multiplier);
    }
    public void addMoney() {

    }
    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public static long getMoney() {
        return money;
    }

    public long getMoneyPerClick() {
        return moneyPerClick;
    }

    public int getMultiplier() {
        return multiplier;
    }

    /*------------------------------------------------
                              Setter
    ------------------------------------------------*/
    public void setMoneyPerClick(long moneyPerClick) {
        this.moneyPerClick = moneyPerClick;
    }

    public static void setMoney(long money) {
        Gibb.money = money;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
