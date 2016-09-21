import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ziver on 2016-09-20.
 */
public class InfiltrationTest {

    @Test
    public void breakDecrypt(){
        String[] input = new String[0];
        assertEquals(null, Infiltration.breakDecrypt(input));

        breakDecrypt(null, "");

//        breakDecrypt("we will avenge our dead parrot arr",
//                "ex eoii jpxbmx cvz uxju sjzzcn jzz");
//
//        breakDecrypt(null, "wl jkd");
//
//        breakDecrypt(null, "dyd jkl cs");
//
//        breakDecrypt(null, "bee");

        breakDecrypt("be", "be");
        breakDecrypt("be", "ab");

        // Test Cases from Per Persson
        breakDecrypt(null,
                "abc bcd cde def efg fgh ghi hij ijk jkl klm lmn mno nop opq pqr qrs rst stu tuv uvw vwx wxy xyz yza zab ab ac ad ae af ag ah ai aj ak al am an ao ap aq ar as at au av aw ax ay az ba bc bd be xhfo zywq");
        breakDecrypt(null, "aaaaaaaaa");
        breakDecrypt("be our rum mbeu be be", "be our rum mbeu be be");
        breakDecrypt("sable ship dead blood", "sable ship dead blood");
        //breakDecrypt(null, "abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc def");
        breakDecrypt(null, "be eb our uor ruo oru uro rou rum urm rmu mur umr mru");
        breakDecrypt("be our rum", "be our rum");
        breakDecrypt("be dead", "eb abca");
        breakDecrypt(null, "ef abca");
        breakDecrypt(null, "abcdefghijklmnopqrstuvwxyz");
    }
    @Test
    public void timeTest(){
        // Test Case from Per Persson
        breakDecrypt(null, "abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc def");
    }
    public void breakDecrypt(String expect, String in){
        String[] input = (in.isEmpty() ? new String[0] : in.split(" "));
        Infiltration.SubstitutionMap map = Infiltration.breakDecrypt(input);
        if (map == null)
            assertEquals(expect, map);
        else
            assertEquals(expect, map.decrypt(input));
    }


    @Test
    public void randomTestCase(){
        for (int i=0; i <200_000; i++) {
            decryptCompare(generateLine());
        }
    }
    public void decryptCompare(String in){
        //System.out.println(in);
        //System.out.flush();
        String[] input = in.split(" ");
        //String expect = InfiltrationCamp.breakDecrypt(in);
        Infiltration.SubstitutionMap map = Infiltration.breakDecrypt(input);
        /*if (map == null)
            assertEquals(expect, map);
        else
            assertEquals(expect, map.decrypt(input));
            */
    }
    public String generateLine(){
        StringBuilder str = new StringBuilder();
        int nrOfWords = 1+(int)(Math.random()*20);
        for (int i=0; i<nrOfWords; ++i){
            if (i != 0) str.append(' ');
            int nrOfLetters = 1+(int)(Math.random()*20);
            for (int j=0; j < nrOfLetters; j++) {
                str.append((char)('a' + (int)(Math.random()*26)));
            }
        }
        return str.toString();
    }


    @Test
    public void mapKeyValidForWord(){
        Infiltration.SubstitutionMap map = new Infiltration.SubstitutionMap();
        assertTrue(map.keyValidForWord("abcd", "abcd"));
        map.applywordToMap("abcd", "abcd");
        assertTrue(map.keyValidForWord("abcd", "abcd"));

        mapKeyValidForWord("xbcd", "abcd",      "abcd", "abcd");
        mapKeyValidForWord("axcd", "abcd",      "abcd", "abcd");
        mapKeyValidForWord("abxd", "abcd",      "abcd", "abcd");
        mapKeyValidForWord("abcx", "abcd",      "abcd", "abcd");

        mapKeyValidForWord("abcd", "xbcd",      "abcd", "abcd");
        mapKeyValidForWord("abcd", "axcd",      "abcd", "abcd");
        mapKeyValidForWord("abcd", "abxd",      "abcd", "abcd");
        mapKeyValidForWord("abcd", "abcx",      "abcd", "abcd");

        // check duplicate references
        map = new Infiltration.SubstitutionMap();
        assertFalse(map.keyValidForWord("fkcegyr", "captain"));
        assertFalse(map.keyValidForWord("captain", "fkcegyr"));
        assertTrue (map.keyValidForWord("be", "ab"));
    }

    private void mapKeyValidForWord(String encAdd, String decAdd, String encValid, String decValid){
        Infiltration.SubstitutionMap map = new Infiltration.SubstitutionMap();
        map.applywordToMap(encAdd, decAdd);
        assertEquals(encAdd.length(), map.size());
        assertFalse(map.keyValidForWord(encValid, decValid));
    }

    @Test
    public void mapCopy(){
        Infiltration.SubstitutionMap original = new Infiltration.SubstitutionMap();
        original.applywordToMap("abcd", "abcd");
        Infiltration.SubstitutionMap copy = original.copy();

        assertEquals(original.hashCode(), copy.hashCode());
        original.applywordToMap("abcd", "lmnk");
        assertNotEquals(original.hashCode(), copy.hashCode());

        assertEquals(4, copy.size());
        assertEquals(4, original.size());

        assertEquals('l', original.decode('a'));
        assertEquals('m', original.decode('b'));
        assertEquals('n', original.decode('c'));
        assertEquals('k', original.decode('d'));

        assertEquals('a', copy.decode('a'));
        assertEquals('b', copy.decode('b'));
        assertEquals('c', copy.decode('c'));
        assertEquals('d', copy.decode('d'));

        assertEquals('a', original.encode('l'));
        assertEquals('b', original.encode('m'));
        assertEquals('c', original.encode('n'));
        assertEquals('d', original.encode('k'));

        assertEquals('a', copy.encode('a'));
        assertEquals('b', copy.encode('b'));
        assertEquals('c', copy.encode('c'));
        assertEquals('d', copy.encode('d'));
    }
}
