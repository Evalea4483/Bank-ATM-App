import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
// setting up vairables

    // first name of the user
    private String firstName;

    // last name of the user
    private String lastName;

    // The ID number of the user
    private String uuid;

    // The MD5 hash of the user's pin number.
    private byte pinHash[];

    // list of accounts for this user
    private ArrayList<Account> accounts;

    //building the constructor
    /**
     * Create a new user
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's account pin number
     * @param theBank the Bank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {

        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's MDF hash, rather than the original value, for
        // security reasons - we use a try/catch to really appease Java... 
        // but when it does catch an error, it will print out that error 
        //message
        try {
             MessageDigest md = MessageDigest.getInstance("MD5");
             this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        
        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);
    }

    /**
     * Add an account for the user
     * @param anAcct  the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Return the user's UUID
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }


/**
 * Check whether a given pin matches the true User pin
 * @param aPin   the pin to check
 * @return       whether the pin is valid or not
 */
    public boolean validatePin(String aPin) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false; 
    }

    /**
     * Retrieves the first name of the User
     * @return returns the first name of the user
     */
    public String getFirstName() {
        return this.firstName;
    }

    public void printAccountsSummary() {

        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * @return the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * print transaction history for a particular account
     * @param acctIdx  the index of the account to use
     */
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    /**
     * Add a transaction to a particular account
     * @param acctIdx
     * @param amount
     * @param memo
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
