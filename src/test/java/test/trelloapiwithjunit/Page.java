package test.trelloapiwithjunit;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.testinium.utility.ApplicationConfig.readFromConfigProperties;
import static org.hamcrest.CoreMatchers.equalTo;

public class Page {
    public static RequestSpecification request;
    public static Response response;
    private String boardID;
    private String listID;
    private List<String> cardID = new ArrayList();
    public static final String key = readFromConfigProperties("config.properties", "key");
    public static final String token = readFromConfigProperties("config.properties", "token");


    public void createBoard(String boardName) {
        JSONObject pathParam = new JSONObject();
        pathParam.put("name", boardName);
        request = RestAssured.given().queryParam("key", key)
                .queryParam("token", token).header("Content-type", "application/json");
        request.body(pathParam.toString());
        response = request.when().post("/boards");
        response.then().statusCode(200).body("name", equalTo(boardName));
        response.getBody().prettyPrint();
        boardID = response.jsonPath().getString("id");
    }

    public void createList(String listName) {
        JSONObject pathParam = new JSONObject();
        pathParam.put("name", listName);
        pathParam.put("idBoard", boardID);
        request.body(pathParam.toString());
        response = request.when().post("/lists");
        response.then().statusCode(200).body("name", equalTo(listName));
        response.getBody().prettyPrint();
        listID = response.jsonPath().getString("id");
    }

    public void createCard(String cardName) {
        JSONObject pathParam = new JSONObject();
        pathParam.put("name", cardName);
        pathParam.put("idList", listID);
        request.body(pathParam.toString());
        response = request.when().post("/cards");
        response.then().statusCode(200).body("name", equalTo(cardName));
        response.getBody().prettyPrint();
        cardID.add(response.jsonPath().getString("id"));
    }

    public void updateCard(String cardName, int randomCard) {
        String id = cardID.get(randomCard);
        JSONObject pathParam = new JSONObject();
        pathParam.put("name", cardName);
        request.body(pathParam.toString());
        response = request.when().put("/cards/" + id);
        response.then().statusCode(200).body("name", equalTo(cardName));
        response.getBody().prettyPrint();
    }

    public void deleteCard(int index) {
        String id = cardID.get(index);
        response = request.when().delete("/cards/" + id);
        response.then().statusCode(200);
        response.getBody().prettyPrint();
    }

    public void deleteBoard() {
        response = request.when().delete("/boards/" + boardID);
        response.then().statusCode(200);
        response.getBody().prettyPrint();
    }

    public boolean verifyStatusCode(){
        if(response.getStatusCode()==200)
            return true;
        else
            return false;
    }

}
