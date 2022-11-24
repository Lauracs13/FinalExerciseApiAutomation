package test;

import base.requests.Request;
import base.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BankTransactionsTests extends BaseTest {


 /*   @Test
    public void verifyTheEndpointIsEmpty() {
        log.info("Deleting all users if they exist");
        request.deleteAllUsers();

        log.info("Validating the endpoint is empty");
        Assert.assertEquals(request.getUsersSize(), 0, "The endpoint is not empty");

    }*/

    @Test
    public void initializeThePOJO() {

        log.info("Validating new users post without repeated email");
        Assert.assertTrue(request.postNewUsersWithUniqueEmailOnly(10, 2));

      //  request.postNewUsersWithUniqueEmailOnly(10, 2);
    }
}
