package base.requests;

import base.model.PojoBankUser;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

/**
 * The Request class where the methods used in tests are established.
 */
public class Request {
    private final String ENDPOINT = "https://637e9ba3cfdbfd9a63b43644.mockapi.io/user";

    /**
     * Gets the quantity of users.
     *
     * @return quantity of users
     */
    public int getUsersSize() {
        List<PojoBankUser> users = getUsers();
        int usersSize = users.size();
        return usersSize;
    }

    /**
     * Gets users throws a GET REQUEST.
     *
     * @return a list with all the users
     */
    public List<PojoBankUser> getUsers() {
        RestAssured.baseURI = ENDPOINT;
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<PojoBankUser> bankUsers = jsonPathEvaluator.getList("", PojoBankUser.class);
        return bankUsers;
    }

    /**
     * Delete all users throws a DELETE method.
     */
    public void deleteAllUsers() {
        if (this.getUsersSize() > 0) {
            RestAssured.baseURI = ENDPOINT;

            Response response = null;
            List<String> allIds = this.getAllIds();
            for (int i = 0; i < allIds.size(); i++) {
                try {
                    response = RestAssured.given()
                            .contentType("application/json")
                            .delete("/" + allIds.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets all users ids in a List.
     *
     * @return the list with ids
     */
    public List<String> getAllIds() {
        List<String> allIds = getUsers().stream().map(element -> {
            return element.getId();
        }).collect(Collectors.toList());
        return allIds;
    }

    /**
     * Creates unique and repeated users with random information.
     *
     * @param quantityUnique    the desirable quantity of unique users
     * @param quantityDuplicate the desirable quantity of repeated users including email account
     * @return the list
     */
    public List<PojoBankUser> randomUsersToCreate(int quantityUnique, int quantityDuplicate) {
        Faker faker = new Faker();
        List<PojoBankUser> newUsers = new ArrayList<>();
        for (int i = 0; i < quantityUnique; i++) {
            newUsers.add(new PojoBankUser(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.finance().creditCard(),
                    faker.number().randomNumber(),
                    faker.options().option("deposit", "payment", "invoice", "withdrawal"),
                    faker.internet().emailAddress(),
                    faker.random().nextBoolean(),
                    faker.country().name(),
                    faker.phoneNumber().phoneNumber()
            ));
        }
        PojoBankUser userToDuplicate = newUsers.get(0);
        for (int i = 0; i < quantityDuplicate; i++) {
            newUsers.add(userToDuplicate);
        }
        return newUsers;
    }
    /**
     * Only posts users with unique email accounts throws POST method.
     *
     * @param quantityUnique    the desirable quantity of unique users
     * @param quantityDuplicate the desirable quantity of repeated users including email account
     * @return an integer with the number of unique email accounts
     */
    public int postNewUsersOnlyWithUniqueEmails(int quantityUnique, int quantityDuplicate) {
        HashSet<String> emailsAlreadyPosted = new HashSet<>();
        List<PojoBankUser> randomUsers = randomUsersToCreate(quantityUnique, quantityDuplicate);
        for (int i = 0; i < randomUsers.size(); i++) {
            PojoBankUser newUser = randomUsers.get(i);
            String newUserEmail = newUser.getEmail();
            if (!emailsAlreadyPosted.contains(newUserEmail)) {
                Response response = given()
                        .contentType("application/json")
                        .body(newUser)
                        .when()
                        .post(ENDPOINT);
                emailsAlreadyPosted.add(newUserEmail);
            }
        }
        return emailsAlreadyPosted.size();
    }

    /**
     * Checks if the users posted have uniques email accounts.
     *
     * @param quantityUniqueUsers    the desirable quantity of unique users
     * @param quantityDuplicateUsers the desirable quantity of repeated users including email account
     * @return the boolean
     */
    public boolean haveNewUsersPostedUniqueEmails(int quantityUniqueUsers, int quantityDuplicateUsers) {
        int uniqueEmailsFound = postNewUsersOnlyWithUniqueEmails(quantityUniqueUsers, quantityDuplicateUsers);
        boolean result = Objects.equals(uniqueEmailsFound, quantityUniqueUsers);
        return result;
    }

    
    /**
     * Checks if users in the API have unique emails.
     *
     * @return the boolean
     */
    public boolean usersHaveUniqueEmails() {
        HashSet<String> emailsChecked = new HashSet<>();
        List<PojoBankUser> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            PojoBankUser user = users.get(i);
            String userEmail = user.getEmail();
            if (!emailsChecked.contains(userEmail)) {
                emailsChecked.add(userEmail);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets an email to be repeated. It can be used to test the usersHaveUniqueEmails() method.
     */
    public void setEmailToBeRepeated() {
        List<PojoBankUser> users = getUsers();
        String emailToBeRepeated = users.get(0).getEmail();
        users.get(1).setEmail(emailToBeRepeated);
        PojoBankUser userUpdated = users.get(1);
        Response response = given().contentType("application/json").body(userUpdated).when().put(ENDPOINT + "/" + userUpdated.getId());
    }

    /**
     * Update and existing account number throws UPDATE method.
     *
     * @return an integer with the status code of the request.
     */
    public int updateAndExistingAccountNumber() {
        List<PojoBankUser> users = getUsers();
        int userIndexToBeUpdated = (int) Math.floor(Math.random() * users.size());
        PojoBankUser userToBeUpdated = users.get(userIndexToBeUpdated);
        Faker faker = new Faker();
        String fakedAccountNumber = faker.finance().creditCard();
        userToBeUpdated.setAccountNumber(fakedAccountNumber);
        Response response = given().contentType("application/json").body(userToBeUpdated).when().put(ENDPOINT + "/" + userIndexToBeUpdated);
        return response.getStatusCode();
    }
}