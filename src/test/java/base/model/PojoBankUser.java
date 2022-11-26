package base.model;

/**
 * The bank user POJO class.
 */
public class PojoBankUser {
    private String id;
    private String name;

    private String lastName;

    private String accountNumber;

    /**
     * The Amount.
     */
    public float amount;

    /**
     * The Transaction type.
     */
    public String transactionType;

    /**
     * The Email.
     */
    public String email;

    /**
     * The Active State.
     */
    public Boolean active;

    /**
     * The Country.
     */
    public String country;

    /**
     * The Telephone.
     */
    public String telephone;

    /**
     * Instantiates a new Pojo bank user.
     *
     * @param name            the name
     * @param lastName        the last name
     * @param accountNumber   the account number
     * @param amount          the amount
     * @param transactionType the transaction type
     * @param email           the email
     * @param active          the active
     * @param country         the country
     * @param telephone       the telephone
     */
    public PojoBankUser(String name, String lastName, String accountNumber, float amount, String transactionType, String email, Boolean active, String country, String telephone) {
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
        }


    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets transaction type.
     *
     * @param transactionType the transaction type
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets telephone.
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets telephone.
     *
     * @param telephone the telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
