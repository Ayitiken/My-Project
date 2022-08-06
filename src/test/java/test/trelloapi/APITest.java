package test.trelloapi;

import org.junit.Test;
import java.util.Random;

public class APITest extends APIBasePage {
    Page page = new Page();
    Random rand = new Random();

    @Test
    public void trelloApiTest() {

        page.createBoard("Simple Board");
        page.createList("the first list");
        page.createCard("The first Card");
        page.createCard("The second Card");
        page.updateCard("Update the Card", rand.nextInt(1));
        page.deleteCard(0);
        page.deleteCard(1);
        page.deleteBoard();

    }
}
