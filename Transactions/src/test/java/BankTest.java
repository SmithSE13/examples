import exceptions.AccountBlockedException;
import exceptions.InsufficientFundsOnAccountException;
import exceptions.NotValidAccountException;
import exceptions.TransferIsNotPossibleWithoutVisitingBankException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BankTest extends Assert {

    private Account firstAccount;
    private Account secondAccount;

    private Bank bank;

    @Before
    public void setUp() {
        firstAccount = new Account("111111",1000000);
        secondAccount = new Account("222222",0);
        bank = new Bank();
        bank.addAccount(firstAccount.getAccNumber(), firstAccount.getMoney().doubleValue());
        bank.addAccount(secondAccount.getAccNumber(), secondAccount.getMoney().doubleValue());
    }

    @Test
    public void testGetBalance() {
        BigDecimal expectedSum = firstAccount.getMoney();
        String actualAccount = firstAccount.getAccNumber();
        assertEquals(expectedSum, bank.getBalance(actualAccount));
    }

    @Test
    public void testTransfer0() {
        firstAccount.subtractMoney(500d);
        secondAccount.addMoney(500d);
        bank.transfer(firstAccount.getAccNumber(), secondAccount.getAccNumber(), 500);
        assertEquals(firstAccount.getMoney(), bank.getBalance(firstAccount.getAccNumber()));
        assertEquals(secondAccount.getMoney(), bank.getBalance(secondAccount.getAccNumber()));
    }

    @Test
    public void testTransfer1() {
        firstAccount.subtractMoney(5656d);
        secondAccount.addMoney(5656d);
        bank.transfer(firstAccount.getAccNumber(), secondAccount.getAccNumber(), 5656);
        assertEquals(firstAccount.getMoney(), bank.getBalance(firstAccount.getAccNumber()));
        assertEquals(secondAccount.getMoney(), bank.getBalance(secondAccount.getAccNumber()));
    }

    @Test(expected = InsufficientFundsOnAccountException.class)
    public void testTransfer2() {
        firstAccount.subtractMoney(5656d);
        secondAccount.addMoney(5656d);
        bank.transfer(secondAccount.getAccNumber(), firstAccount.getAccNumber(), 555);
    }

    /*Throws exception if variable Bank#random is true, else the test not passed.
    If variable Bank#random is true then in console write "Счет заблокирован. Обратитесь в банк."*/
    @Test(expected = AccountBlockedException.class)
    public void testTransfer3() {
        bank.transfer("111111", "222222", 50001);
    }

    @Test(expected = TransferIsNotPossibleWithoutVisitingBankException.class)
    public void testTransfer4() {
        firstAccount = bank.getAccount("111111");
        firstAccount.setBlocked(true);
        secondAccount = bank.getAccount("222222");
        secondAccount.setBlocked(true);
        bank.transfer(firstAccount.getAccNumber(), secondAccount.getAccNumber(), 50001);
    }

    @Test(expected = NotValidAccountException.class)
    public void testTransfer5() {
        bank.transfer("111111", "111111", 50001);
    }

    @Test(expected = TransferIsNotPossibleWithoutVisitingBankException.class)
    public void testTransfer6() {
        firstAccount = bank.getAccount("111111");
        firstAccount.setBlocked(true);
        bank.transfer("111111", "222222", 5000);
    }

    @After
    public void tearDown() {
        firstAccount = null;
        secondAccount = null;
        bank = null;
    }
}
