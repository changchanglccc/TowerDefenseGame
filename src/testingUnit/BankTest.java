package testingUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.bankaccount.BankAccount;


/**
 * Teasing setting the player Balance
 *@author m_lzahra
 *@version 1.0
 *@since 16/2/2016
 */
public class BankTest {

    private BankAccount bankAccount;
    private double  afterAddingBalance;
    private double beforeAddingBalance;

    /**
     * setting values.
     */
    @Before 
    public void setValues() {
        bankAccount = new BankAccount();
    }

    /**
     * Testing setting the player Balance.
     */
    @Test
    public void testSetBalance() {
        beforeAddingBalance = bankAccount.getBalance();
        bankAccount.setBalance(100);
        afterAddingBalance =  bankAccount.getBalance() ;
        assertTrue(beforeAddingBalance < afterAddingBalance );
    }
    /**
     * Testing player deposit.
     */
    @Test
    public void testdeposit() {
        beforeAddingBalance = bankAccount.getBalance();
        bankAccount.deposit(100);
        afterAddingBalance =  bankAccount.getBalance() ;
        assertTrue(beforeAddingBalance < afterAddingBalance );
    }
    /**
     * Testing player withdraw.
     */
    @Test
    public void testWithdraw() {
        bankAccount.setBalance(100);
        beforeAddingBalance = bankAccount.getBalance();
        bankAccount.withDraw(20);
        afterAddingBalance =  bankAccount.getBalance() ;
        assertTrue(beforeAddingBalance > afterAddingBalance );
    }
}
