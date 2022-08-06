package test.trelloapi;

import io.restassured.RestAssured;
import org.junit.Before;

import static com.testinium.utility.ApplicationConfig.readFromConfigProperties;

public class APIBasePage {

    @Before
    public void before() {

        RestAssured.baseURI = readFromConfigProperties("config.properties", "baseurl");

    }
}
