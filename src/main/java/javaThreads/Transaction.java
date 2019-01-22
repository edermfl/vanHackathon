package javaThreads;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private Account account;

    private List<String> transactions;

    public Transaction(final Account pAccount) {
	account = pAccount;
	transactions = new ArrayList<>();
    }

    /**
     * void deposit(int money) to invoke the deposit method in the Account class. This should add the transaction message to the transactions list.
     *
     * @param pMoney
     */
    public synchronized void deposit(int pMoney) {
	transactions.add(account.deposit(pMoney));
    }

    /**
     * List<String> getTransaction() to return the transactions.
     *
     * @return List of transactions
     */
    public List<String> getTransaction() {
	return transactions;
    }

    /**
     * void withdraw(int money) to invoke the withdraw method in the Account class. This should add the transaction message to the transactions list.
     *
     * @param pMoney
     */
    public synchronized void withdraw(int pMoney) {
	transactions.add(account.withdraw(pMoney));
    }
}
