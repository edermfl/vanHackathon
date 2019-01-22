package javaThreads;

public class Account {

    private int balance = 0;

    /**
     * String deposit(int pMoney) to add pMoney to the balance. This method should return a string that describes the deposit transaction, i.e., "Depositing $pMoney".
     *
     * @param pMoney
     * @return message
     */
    public String deposit(int pMoney) {
	balance += pMoney;
	return "Depositing $" + pMoney;
    }

    /**
     * int getBalance() to return the account balance.
     *
     * @return balance
     */
    public int getBalance() {
	return balance;
    }

    /**
     * String withdraw(int pMoney) to subtract pMoney from the balance. This method should return a string that describes the withdraw transaction, i.e., "Withdrawing $pMoney". Note that, if there is insufficient balance to successfully withdraw the desired amount, then the balance should not be adjusted, and the returned string should be "Withdrawing $pMoney (Insufficient Balance)".
     *
     * @param pMoney
     * @return message
     */
    public String withdraw(int pMoney) {
	final boolean isEnough = balance >= pMoney;
	if (isEnough) {
	    balance -= pMoney;
	    return "Withdrawing $" + pMoney;
	}
	return "Withdrawing $" + pMoney + " (Insufficient Balance)";
    }
}
