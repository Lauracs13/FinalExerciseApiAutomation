package test;

import base.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BankTransactionsTests extends BaseTest {


  /*  @Test
    public void verifyTheEndpointIsEmpty() {
        log.info("Deleting all users if they exist");
        request.deleteAllUsers();

        log.info("Validating the endpoint is empty");
        Assert.assertEquals(request.getUsersSize(), 0, "The endpoint is not empty");

    }

    @Test
    public void initializeThePOJO() {
        log.info("Validating new users are posted with unique email accounts");
        Assert.assertTrue(request.postNewUsersOnlyWithUniqueEmails(10, 2), "New users are posted with repeated email accounts");
    }*/

/*    @Test
    public void getAllUsers() {
        request.deleteAllUsers();
        request.postNewUsersOnlyWithUniqueEmails(5, 2);

        log.info("Validating users have unique email accounts");
        Assert.assertTrue(request.usersHaveUniqueEmails(), "Users do not have unique email accounts");

     *//*   request.setEmailToBeRepeated();

        log.info("Validating users have unique email accounts");
        Assert.assertTrue(request.usersHaveUniqueEmails(), "Users do not have unique email accounts");*//*
    }*/
    @Test
    public void updateAnExistingAccountNumber(){
        log.info("Validate that an existing Account Number is correctly updated");
        Assert.assertEquals(request.updateAndExistingAccountNumber(), 200, "Account Number is not updated");

    }
}
