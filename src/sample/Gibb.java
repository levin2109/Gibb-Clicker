package sample;

import domain.UserJDBCDoa;

import java.util.List;

public class Gibb {
    public static long balance;
    private long moneyPerClick = 1;
    private int multiplier = 1;

    /*------------------------------------------------
                        Constructor
    ------------------------------------------------*/

    public Gibb(long balance) {
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

    public void addBalance(long amount) {
        balance = balance + amount;
    }

    public long calcMoneyPerSecond(List<Tools> toolsList) {
        long money = 0;
        for (Tools tool : toolsList) {
            if (tool.isStatus()) {
                money = money + tool.getMoneyPerSecond();
            }
        }
        return money;
    }

    public long addMoney(List<Tools> toolsList) {
        addBalance(calcMoneyPerSecond(toolsList) / 5);
        return calcMoneyPerSecond(toolsList) / 5;
    }
    /*------------------------------------------------
                          Getter
    ------------------------------------------------*/
    public static long getBalance() {
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

    public static void setBalance(long balance) {
        Gibb.balance = balance;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
