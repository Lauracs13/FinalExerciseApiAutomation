package test;

import base.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Bank transactions tests class with the test cases.
 */
public class BankTransactionsTests extends BaseTest {
    /**
     * Verify the endpoint is empty.
     */
    @Test(priority = 1)
    public void verifyTheEndpointIsEmpty() {
        log.info("Deleting all users if they exist");
        request.deleteAllUsers();

        log.info("Validating the endpoint is empty");
        Assert.assertEquals(request.getUsersSize(), 0, "The endpoint is not empty");
    }

    /**
     *  Initialize the POJO with 10 random data, avoiding duplicate email accounts.
     */
    @Test(priority = 2)
    public void initializeThePOJO() {
        log.info("Validating new users are posted with unique email accounts");
        Assert.assertTrue(request.haveNewUsersPostedUniqueEmails(10, 2), "New users are posted with repeated email accounts");
    }

    /**
     * Gets all users asserting that there are not duplicate email accounts.
     */
    @Test(priority = 3)
    public void getAllUsers() {
        log.info("Validating users have unique email accounts");
        Assert.assertTrue(request.usersHaveUniqueEmails(), "Users do not have unique email accounts");
    }

    /**
     * Update an existing account number.
     */
    @Test(priority = 4)
    public void updateAnExistingAccountNumber() {
        log.info("Validate that an existing Account Number is correctly updated");
        Assert.assertEquals(request.updateAndExistingAccountNumber(), 200, "Account Number is not updated");
    }
}
