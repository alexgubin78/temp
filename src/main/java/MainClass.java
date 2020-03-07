public class MainClass {
    public static void main(String[] args) {
        final BankAccount account = new BankAccount(1000, "12345-678");
        Thread thread_1 = new Thread(new Runnable() {
            public void run() {
                   account.deposit(300);
                   account.withdraw(50);
                   account.printAccountNumber();
                   String accountNumber = account.getAccountNumber();
                System.out.println("Thread 1: Account number = ".concat(accountNumber));
            }
        });
        Thread thread_2 = new Thread(new Runnable() {
            public void run() {
                    account.deposit(203.75);
                    account.withdraw(100);
                    String accountNumber = account.getAccountNumber();
                    account.printAccountNumber();

                System.out.println("Thread 2: Account number = ".concat(accountNumber));
            }
        });
        thread_1.setName("-=THREAD 1=-");
        thread_2.setName("-=THREAD 2=-");
        thread_1.start();
        thread_2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account);}

}
