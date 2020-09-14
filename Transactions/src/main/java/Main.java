import java.math.BigDecimal;

public class Main {
    private static Bank bank = new Bank();
    private static BigDecimal before = new BigDecimal(0);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            String numAcc = String.valueOf(i);
            double money = Math.random() * 1000000;
            bank.addAccount(numAcc, money);
            if (i == 9) {
                before = bank.getSum();
            }
        }


        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Go());
            thread.start();

        }
        try {
            Thread.sleep(30000);
            System.out.println(before.compareTo(bank.getSum()));
            System.out.println(before + " " + bank.getSum());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public static class Go implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 1000; i++) {
                String numAccFirst = String.valueOf((int) (Math.random() * 9));
                String numAccSecond = String.valueOf((int) (Math.random() * 9));
                double sum = Math.random() * 100000;
                System.out.println("====================");
                System.out.println("Account number : " + numAccFirst + " " + bank.getBalance(numAccFirst));
                System.out.println("Account number : " + numAccSecond + " " + bank.getBalance(numAccSecond));
                bank.transfer(numAccFirst, numAccSecond, sum);
                System.out.println("Account number : " + numAccFirst + " " + bank.getBalance(numAccFirst));
                System.out.println("Account number : " + numAccSecond + " " + bank.getBalance(numAccSecond));
                System.out.println("====================");
            }
            System.out.println(before.compareTo(bank.getSum()));
            System.out.println(before + " " + bank.getSum());
        }
    }
}
