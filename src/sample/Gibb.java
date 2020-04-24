package sample;

import domain.UserJDBCDoa;

public class Gibb {
    public static double balance;
    private long moneyPerClick;
    private int multiplier;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/

    public Gibb(double balance) {
        this.balance = balance;
    }

    /*------------------------------------------------
                         Methods
    ------------------------------------------------*/
    public void click() {
        balance = balance + (moneyPerClick*multiplier);
    }

    public void loadBalance(String username) {
        UserJDBCDoa User = new UserJDBCDoa();
        balance = User.getBalance(username);
    }

    public void addMoneyPerSecond() {

    }

    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public static double getBalance() {
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

    public static void setBalance(double balance) {
        Gibb.balance = balance;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
