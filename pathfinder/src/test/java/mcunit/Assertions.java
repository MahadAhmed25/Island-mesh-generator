package mcunit;

public class Assertions {
    public static void assertTrue(boolean condition){
        if(!condition){
            throw new AssertionError();
        }
    }

    public static void assertFalse(boolean condition){
        assertTrue(!condition);
    }

    public static void assertEquals(Object actual, Object expected){
        assertTrue(actual.equals(expected));
    }

    public static void assertNotEquals(Object left, Object right) {
        assertFalse(left.equals(right));
    }

    
}
