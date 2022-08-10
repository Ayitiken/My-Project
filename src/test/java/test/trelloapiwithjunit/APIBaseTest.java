package test.trelloapiwithjunit;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import test.uitestframework.TestResultLogger;
import static com.testinium.utility.ApplicationConfig.readFromConfigProperties;

@ExtendWith(TestResultLogger.class)
public class APIBaseTest {

    @BeforeEach
    public void before() {
        RestAssured.baseURI = readFromConfigProperties("config.properties", "baseurl");
    }

    @AfterEach
    public void after(TestInfo testInfo) {
        String callingTest = testInfo.getTestMethod().get().getName();
        System.out.println(callingTest + " PASSED !!");
    }
}
