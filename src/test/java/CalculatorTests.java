import com.shozy.Parser;
import com.shozy.Statement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {

    @Test
    public void testSimpleAddition() {
        Parser parser = new Parser("3 + 5");
        Statement statement = parser.parse();
        assertEquals(8.0, statement.evaluate());
    }

    @Test
    public void testSimpleSubtraction() {
        Parser parser = new Parser("10 - 4");
        Statement statement = parser.parse();
        assertEquals(6.0, statement.evaluate());
    }

    @Test
    public void testMultiplication() {
        Parser parser = new Parser("6 * 7");
        Statement statement = parser.parse();
        assertEquals(42.0, statement.evaluate());
    }

    @Test
    public void testDivision() {
        Parser parser = new Parser("20 / 4");
        Statement statement = parser.parse();
        assertEquals(5.0, statement.evaluate());
    }

    @Test
    public void testOperatorPrecedence() {
        Parser parser = new Parser("3 + 5 * 2");
        Statement statement = parser.parse();
        assertEquals(13.0, statement.evaluate());
    }

    @Test
    public void testComplexExpression() {
        Parser parser = new Parser("3 + 5 * 2 - 8 / 4");
        Statement statement = parser.parse();
        assertEquals(11.0, statement.evaluate());
    }

    @Test
    public void testDecimalNumbers() {
        Parser parser = new Parser("2.5 * 4");
        Statement statement = parser.parse();
        assertEquals(10.0, statement.evaluate());
    }

    @Test
    public void testDivisionByZero() {
        Parser parser = new Parser("10 / 0");
        Statement statement = parser.parse();
        assertTrue(Double.isInfinite(statement.evaluate()));
    }

    @Test
    public void testInvalidCharacter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Parser parser = new Parser("3 + 5 & 2");
            parser.parse();
        });
        assertTrue(exception.getMessage().contains("Unexpected character"));
    }

    @Test
    public void testEmptyInput() {
        Parser parser = new Parser("");
        Statement statement = parser.parse();
        assertTrue(Double.isNaN(statement.evaluate()));
    }
}