import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();

    public BankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }
    public void deposit(double amount){
        boolean flag = false;

        try{
            flag = lock.tryLock(1, TimeUnit.MICROSECONDS);
        }catch (InterruptedException e){
            System.out.println("ERROR ".concat(e.getMessage()));
            e.printStackTrace();
        }

        if (flag){
        try{
            balance += amount;
        }finally {
            lock.unlock();
        }}else{
            System.out.println("Could not get the lock!");
        }
    }
    public void withdraw(double amount){
        boolean flag = false;

        try{
            flag = lock.tryLock(1, TimeUnit.MICROSECONDS);
           // Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("ERROR ".concat(e.getMessage()));
            e.printStackTrace();
        }

        if (flag){
            try{
                balance -= amount;
            }finally {
                lock.unlock();
            }}else{
            System.out.println("Could not get the lock!");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account Number: ".concat(this.accountNumber));
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
