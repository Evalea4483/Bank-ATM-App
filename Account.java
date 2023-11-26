import java.util.ArrayList;

public class Account {

    // Name of the account
    private String name;

    // The account ID number;
    private String uuid;

    // The user object that owns the account
    private User holder; 

    // The list of transactions for the account
    private ArrayList<Transaction> transactions;


    // Building the constructor
    /**
     * Create a new account
     * @param name the name of the account
     * @param holder the User object that holds this account
     * @param theBank the bank that issues the account
     */
    public Account(String name, User holder, Bank theBank) {

        //set the account name and holder
        this.name = name;
        this.holder = holder;

        // get next account UUID
        this.uuid = theBank.getNewAccountUUID();

        //init transactions of the account
        this.transactions = new ArrayList<Transaction>();

    }
    
    /**
     * Get the account ID
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }


    /**
     * Get summary line for the account
     * @return the string summary
     */
    public String getSummaryLine() {

        // get account's balance
        double balance = this.getBalance();

        // format the summary line, depending on whether the balance is
        //negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s",
               this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s",
               this.uuid, balance, this.name);
        }
    }


    /**
     * Get the balance of this account by adding the amounts of the transactions
     * @return
     */
    public double getBalance() {

        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * print the transaction history of the account
     */
    public void printTransHistory() {

        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size()-1; t >= 0; t--) {
            System.out.printf("%s\n",this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }


    /**
     * Add a new transaction to this account
     * @param amount  the amount transacted
     * @param memo    the transaction memo
     */
    public void addTransaction(double amount, String memo) {

        //create new transaction object and add it to the list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
