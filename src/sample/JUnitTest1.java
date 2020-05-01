package sample;

import org.junit.Test;

public class JUnitTest1 {


    @Test

    public void updateMoneyPerSecondTest() {
        long moneyPerSecond = 0;
        long moneyPerSecondNormal = 10;
        int level = 5;
        int multiplier = 2;
        moneyPerSecond = moneyPerSecondNormal * level * multiplier;
    }

    @Test
    public void addBalanceTest() {
        double balance = 10;
        double amount = 550;
        balance = balance + amount;
    }
}
