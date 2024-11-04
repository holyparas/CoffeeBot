import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CoffeeBotTest {

    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(new FileInputStream("test1.in"));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testCoffeeBotOutput() {
        CoffeeBot.main(new String[]{"10", "10"});
        assertTrue(outputStreamCaptor.toString().contains("Thank you"));
    }
}
