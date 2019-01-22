package digitalWallet;

public class DigitalWalletTransaction {

    /**
     * add money to the wallet. It should also throw the required exceptions for any invalid transactions.
     *
     * @param digitalWallet
     * @param amount
     */
    public void addMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
	generalValidationForWallet(digitalWallet, amount);
	digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() + amount);
    }

    /**
     * make a payment from the wallet. It should also throw the required exceptions for any invalid transactions.
     *
     * @param digitalWallet
     * @param amount
     */
    public void payMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
	generalValidationForWallet(digitalWallet, amount);
	if (digitalWallet.getWalletBalance() < amount) {
	    throw new TransactionException("Insufficient balance","INSUFFICIENT_BALANCE");
	}
	digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() - amount);
    }

    private void generalValidationForWallet(final DigitalWallet digitalWallet, final int pAmount) throws TransactionException {
	if (digitalWallet.getUserAccessToken() == null || digitalWallet.getUserAccessToken().isEmpty()) {
	    throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
	}
	if (pAmount <= 0) {
	    throw new TransactionException("Amount should be greater than zero","INVALID_AMOUNT");
	}
    }
}
