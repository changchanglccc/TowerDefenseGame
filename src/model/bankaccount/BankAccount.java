package model.bankaccount;

/**
 * Bank account for the game
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public class BankAccount {

    public static int INITIAL_BALANCE = 5000;
    private double balance;

    /**
     * getter method for balance
     * @return balance
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * setter method for balance
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * add more gold to the account
     * @param gold
     */
    public void deposit(double gold) {
        balance += gold;
    }

    /**
     * withdraw some gold from account
     * @param gold
     */
    public void withDraw(double gold) {
        balance -= gold;
    }
}
