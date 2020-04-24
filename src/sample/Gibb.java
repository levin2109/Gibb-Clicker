package sample;

public class Gibb {
    public static long balance;
    private long moneyPerClick;
    private int multiplier;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/

    public Gibb(long balance, long moneyPerClick) {
        this.balance = balance;
        this.moneyPerClick = moneyPerClick;
    }

    /*------------------------------------------------
                         Methods
    ------------------------------------------------*/
    public void click() {

    }

    public void calcMoneyPerClick() {

    }

    public void addMoneyPerSecond() {
        balance = balance + (moneyPerClick*multiplier);
    }

    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public static long getMoney() {
        return balance;
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
        Gibb.balance = money;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
