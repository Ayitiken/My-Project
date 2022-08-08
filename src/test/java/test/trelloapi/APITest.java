package test.trelloapi;

import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class APITest extends APIBaseTest {
    Page page = new Page();
    Random rand = new Random();

    @Test
    public void trelloApiTest() {

        page.createBoard("Simple Board");
        assertTrue(page.verifyStatusCode());
        page.createList("the first list");
        assertTrue(page.verifyStatusCode());
        page.createCard("The first Card");
        assertTrue(page.verifyStatusCode());
        page.createCard("The second Card");
        assertTrue(page.verifyStatusCode());
        page.updateCard("Update the Card", rand.nextInt(1));
        assertTrue(page.verifyStatusCode());
        page.deleteCard(0);
        assertTrue(page.verifyStatusCode());
        page.deleteCard(1);
        assertTrue(page.verifyStatusCode());
        page.deleteBoard();
        assertTrue(page.verifyStatusCode());
    }
}
