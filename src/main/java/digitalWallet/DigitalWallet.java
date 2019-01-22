package digitalWallet;

public class DigitalWallet {
    private String userAccessCode;

    private String username;

    private int walletBalance;

    private String walletId;

    public DigitalWallet(String walletId, String username) {
	this.walletId = walletId;
	this.username = username;
    }

    public DigitalWallet(String walletId, String username, String userAccessCode) {
	this(walletId, username);
	this.userAccessCode = userAccessCode;
    }

    public String getUserAccessToken() {
	return userAccessCode;
    }

    public String getUsername() {
	return username;
    }

    public int getWalletBalance() {
	return walletBalance;
    }

    public String getWalletId() {
	return walletId;
    }

    public void setWalletBalance(int walletBalance) {
	this.walletBalance = walletBalance;
    }

}