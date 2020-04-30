package sample;

import domain.UserJDBCDoa;

import java.util.List;

public class Gibb {
    public static double balance;
    private long moneyPerClick = 1;
    private int multiplier = 1;

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

    public void addBalance(double amount) {
        balance = balance + amount;
    }

    public double calcMoneyPerSecond(List<Tools> toolsList) {
        double money = 0;
        for (Tools tool : toolsList) {
            if (tool.isStatus()) {
                money = money + tool.getMoneyPerSecond();
            }
        }
        return money;
    }

    public double addMoney(List<Tools> toolsList) {
        addBalance(calcMoneyPerSecond(toolsList) / 5);
        return calcMoneyPerSecond(toolsList) / 5;
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
