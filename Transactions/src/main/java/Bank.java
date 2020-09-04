import exceptions.AccountBlockedException;
import exceptions.InsufficientFundsOnAccountException;
import exceptions.NotValidAccountException;
import exceptions.TransferIsNotPossibleWithoutVisitingBankException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private final HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private final Logger logger = LogManager.getLogger();

    private static final double AMOUNT_FOR_CHECK = 50000d;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, double amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, double amount) {
        try {
            checkNumAccounts(fromAccountNum, toAccountNum);
            Account from = accounts.get(fromAccountNum);
            Account to = accounts.get(toAccountNum);
            checkAccounts(amount, from, to);
            Account lowSyncAccount = from;
            Account topSyncAccount = to;
            Comparator<Account> comparator = Comparator.comparing(Account::getAccNumber);
            if (comparator.compare(from, to) > 0) {
                lowSyncAccount = to;
                topSyncAccount = from;
            }
            synchronized (lowSyncAccount) {
                synchronized (topSyncAccount) {
                    from.subtractMoney(amount);
                    to.addMoney(amount);
                    System.out.printf("Перевод с %s на %s на сумму %.2f выполнен \n",
                            fromAccountNum, toAccountNum, amount);
                }
            }
            if (amount > AMOUNT_FOR_CHECK && isFraud(fromAccountNum, toAccountNum, amount)) {
                blockAccount(fromAccountNum, toAccountNum);
                throw new AccountBlockedException();
            }
        } catch (Exception ex) {
            logger.info("{}, перевод с {}, на {} сумма {}", ex.getClass(), fromAccountNum, toAccountNum, amount);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public BigDecimal getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }

    public void addAccount(String accountNum, double sumMoney) {
        Account account = new Account(accountNum, sumMoney);
        accounts.put(accountNum, account);
    }

    public Account getAccount(String accountNum) {
        return accounts.get(accountNum);
    }

    private void checkAccounts(double sum, Account fromAccount, Account toAccount) throws InterruptedException {
        BigDecimal sumBD = new BigDecimal(sum);
        if (fromAccount.isBlocked() || toAccount.isBlocked()) {
            throw new TransferIsNotPossibleWithoutVisitingBankException();
        }
        if (fromAccount.getMoney().compareTo(sumBD) < 0) {
            throw new InsufficientFundsOnAccountException();
        }
    }

    private void checkNumAccounts(String from, String to) {
        if (from.equals(to)) {
            throw new NotValidAccountException();
        }
    }

    private void blockAccount(String fromAccount, String toAccount) {
        accounts.get(fromAccount).setBlocked(true);
        accounts.get(toAccount).setBlocked(true);
        System.out.println("Перевод с " + fromAccount + "  на "
                + toAccount + " заблокирован. Обратитесь в банк.");
    }

    public BigDecimal getSum() {
        return accounts.values().stream().map(Account::getMoney).reduce(BigDecimal::add).get();
    }
}
