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
    //add money for click per second
    public void click() {
        balance = balance + (moneyPerClick*multiplier);
    }

    //load balance from database
    public void loadBalance(String username) {
        UserJDBCDoa User = new UserJDBCDoa();
        balance = User.getBalance(username);
    }

    //add somethin to balance
    public void addBalance(double amount) {
        balance = balance + amount;
    }

    //calculate the money perSecond / 5
    public double calcMoneyPerSecond(List<Tools> toolsList) {
        double money = 0;
        for (Tools tool : toolsList) {
            if (tool.isStatus()) {
                money = money + tool.getMoneyPerSecond();
            }
        }
        return money;
    }

    //add mony to balance
    public double addMoney(List<Tools> toolsList) {
        addBalance(calcMoneyPerSecond(toolsList) / 5);
        return calcMoneyPerSecond(toolsList) / 5;
    }

    //load multiplier per click
    public void loadMultiplier(List<Upgrades> upgradesList) {
        String[] powerups = new String[]{"Geld pro Klick erhöhen 1 Mal","Geld pro Klick erhöhen 2 Mal","Geld pro Klick erhöhen 3 Mal","Geld pro Klick erhöhen 4 Mal","Geld pro Klick erhöhen 5 Mal","Geld pro Klick erhöhen 6 Mal","Geld pro Klick erhöhen 7 Mal","Geld pro Klick erhöhen 8 Mal","Geld pro Klick erhöhen 9 Mal","Geld pro Klick erhöhen 10 Mal"};
        for (Upgrades powerup : upgradesList) {
            for (int j = 0; j < powerups.length; j++) {
                if (powerup.getName().equals(powerups[j])) {
                    if (this.multiplier == 1) {
                        this.multiplier = powerup.getMultiplier();
                    } else {
                        this.multiplier = this.multiplier + powerup.getMultiplier();
                    }
                }
            }
        }
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
