import java.util.Date;

public class Transaction {

    // The amount of the transaction
    private double amount;

    // The time and date of the transaction
    private Date timestamp;

    // A memo for the transaction
    private String memo;

    // The account in which the transcation was performed
    private Account inAccount;

    /**
     * Creater a new transaction
     * @param amount      the amount transacted
     * @param inAccount   the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transation
     * @param amount     the amount transacted
     * @param memo       the moo for the transaction
     * @param inAccount  the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount) {

        // call the two-arg constructor
        this(amount, inAccount);

        //set the memo
        this.memo = memo;
    }


    /**
     * Get the amount of the transaction
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string summarizing the transaction
     * @return the summary string
     */
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), 
                this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), 
                this.amount, this.memo);
        }
    }
}
