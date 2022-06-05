package experiment2;

import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;

public class ArgsTest extends TestCase {
    @Test
    public void test() throws ParseException {
        String arg[] = {"-l", "-p", "8080", "-d", "string"};
        Args args = new Args("l,p#,d*", arg);
        assertTrue(args.isValid());
        boolean logging = args.getBoolean('l');
        int port = args.getInt('p');
        String directory = args.getString('d');
        assert logging;
        assert port == 8080;
        assert directory.equals("string");
    }

    @Test
    public void test1() throws ParseException {
        Args args = new Args("x##", new String[]{"-x", "42.3"});
        assertTrue(args.isValid());
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42.3, args.getDouble('x'), .001);
    }
}
