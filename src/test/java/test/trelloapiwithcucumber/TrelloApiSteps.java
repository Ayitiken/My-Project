package test.trelloapiwithcucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;
import test.trelloapiwithjunit.Page;

import java.util.Random;

import static com.testinium.utility.ApplicationConfig.readFromConfigProperties;


public class TrelloApiSteps {

    Page page = new Page();
    Random rand = new Random();


    @Given("given baseUri")
    public void givenBaseUri() {
        RestAssured.baseURI = readFromConfigProperties("config.properties", "baseurl");
    }

    @When("user should be able to send post request for creating a board {string}")
    public void userShouldBeAbleToSendPostRequestForCreatingABoard(String arg0) {
        page.createBoard(arg0);
    }

    @And("user should be able to send post request for creating a list {string}")
    public void userShouldBeAbleToSendPostRequestForCreatingAList(String arg0) {
        page.createList(arg0);
    }

    @And("user should be able to send post request for creating the first card {string}")
    public void userShouldBeAbleToSendPostRequestForCreatingTheFirstCard(String arg0) {
        page.createCard(arg0);
    }

    @And("user should be able to send post request for creating the second card {string}")
    public void userShouldBeAbleToSendPostRequestForCreatingTheSecondCard(String arg0) {
        page.createCard(arg0);
    }

    @And("user should be able to send put request for updating one of the card {string}")
    public void userShouldBeAbleToSendPutRequestForUpdatingOneOfTheCard(String arg0) {
        page.updateCard(arg0, rand.nextInt(1));
    }

    @And("user should be able to send delete request for deleting the first card")
    public void userShouldBeAbleToSendDeleteRequestForDeletingTheFirstCard() {
        page.deleteCard(0);
    }

    @And("user should be able to send delete request for deleting the second card")
    public void userShouldBeAbleToSendDeleteRequestForDeletingTheSecondCard() {
        page.deleteCard(1);
    }

    @And("user should be able to send delete request for deleting the board")
    public void userShouldBeAbleToSendDeleteRequestForDeletingTheBoard() {
        page.deleteBoard();
    }

    @Then("verify status code")
    public void verifyStatusCode() {
        Assert.assertTrue(page.verifyStatusCode());
    }

}
