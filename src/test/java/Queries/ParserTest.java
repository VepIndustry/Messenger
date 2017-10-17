package Queries;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void parseName() throws Exception {
        assertEquals(Parser.parseName("fdsfdsfdd|dsfsdfd"), "fdsfdsfdd");
        assertEquals(Parser.parseName("fds[]  dfdg df!@|dsfsdfd"), "fds[]  dfdg df!@");

        try {
            Parser.parseName("|dsfsdfd");
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            Parser.parseName("dsfsdfd");
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            Parser.parseName("");
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            Parser.parseName(null);
            fail();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseValues() throws Exception {
    }

}