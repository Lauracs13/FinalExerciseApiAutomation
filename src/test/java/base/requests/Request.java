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

public class Request {
    private final String ENDPOINT = "https://637e9ba3cfdbfd9a63b43644.mockapi.io/user";

    public int getUsersSize() {
        List<PojoBankUser> users = getUsers();
        int usersSize = users.size();
        return usersSize;
    }

    public List<PojoBankUser> getUsers() {
        RestAssured.baseURI = ENDPOINT;
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("");
        // response.prettyPrint();
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<PojoBankUser> bankUsers = jsonPathEvaluator.getList("", PojoBankUser.class);
        return bankUsers;
    }

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

    public List<String> getAllIds() {
        List<String> allIds = getUsers().stream().map(element -> {
            return element.getId();
        }).collect(Collectors.toList());
        System.out.println(allIds);
        return allIds;
    }

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

    public boolean postNewUsersOnlyWithUniqueEmails(int quantityUnique, int quantityDuplicate) {
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
        int emailsAlreadyPostedSize = emailsAlreadyPosted.size();
        boolean result = Objects.equals(emailsAlreadyPostedSize, quantityUnique);
        return result;
    }

    public boolean usersHaveUniqueEmails() {
        HashSet<String> emailsAlreadyPosted = new HashSet<>();
        List<PojoBankUser> users = getUsers();
        for (int i = 0; i < users.size(); i++) {
            PojoBankUser user = users.get(i);
            String userEmail = user.getEmail();
            if (!emailsAlreadyPosted.contains(userEmail)) {
                emailsAlreadyPosted.add(userEmail);
            } else {
                return false;
            }
        }
        return true;
    }

    public void setEmailToBeRepeated() {
        List<PojoBankUser> users = getUsers();
        String emailToBeRepeated = users.get(0).getEmail();
        users.get(1).setEmail(emailToBeRepeated);
        PojoBankUser userUpdated = users.get(1);
        Response response = given().contentType("application/json").body(userUpdated).when().put(ENDPOINT + "/" + userUpdated.getId());
    }

    public int updateAndExistingAccountNumber() {
        int userIndexToBeUpdated = (int) (Math.random() * getUsersSize());
        PojoBankUser userToBeUpdated = getUsers().get(userIndexToBeUpdated);
        Faker faker = new Faker();
        String fakedAccountNumber = faker.finance().creditCard();
        userToBeUpdated.setAccountNumber(fakedAccountNumber);
        Response response = given().contentType("application/json").body(userToBeUpdated).when().put(ENDPOINT + "/" + userIndexToBeUpdated);
        return response.getStatusCode();
    }
}