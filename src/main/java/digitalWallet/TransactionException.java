package digitalWallet;

public class TransactionException extends Exception {

    public static TransactionException INSUFFICIENT_BALANCE = new TransactionException("Insufficient balance",
		    "INSUFFICIENT_BALANCE");

    public static TransactionException INVALID_AMOUNT = new TransactionException("Amount should be greater than zero",
		    "INVALID_AMOUNT");

    public static TransactionException USER_NOT_AUTHORIZED = new TransactionException("User not authorized",
		    "USER_NOT_AUTHORIZED");

    private String errorCode;

    public TransactionException(String errorMessage, String errorCode) {
	super(errorMessage);
	this.errorCode = errorCode;
    }

    public String getErrorCode() {
	return errorCode;
    }

}
