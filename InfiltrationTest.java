import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ziver on 2016-09-20.
 */
public class InfiltrationTest {

    @Test
    public void breakDecrypt(){
        String[] input = new String[0];
        assertEquals(null, Infiltration.breakDecrypt(input));

        breakDecrypt("we will avenge our dead parrot arr",
                "ex eoii jpxbmx cvz uxju sjzzcn jzz");

        breakDecrypt(null, "wl jkd");

        breakDecrypt(null, "dyd jkl cs");

        breakDecrypt(null, "bee");

        breakDecrypt("be", "be");

        // Test from Per Persson
        breakDecrypt(null,
                "abc bcd cde def efg fgh ghi hij ijk jkl klm lmn mno nop opq pqr qrs rst stu tuv uvw vwx wxy xyz yza zab ab ac ad ae af ag ah ai aj ak al am an ao ap aq ar as at au av aw ax ay az ba bc bd be xhfo zywq");
        breakDecrypt(null, "aaaaaaaaa");
        breakDecrypt("be our rum mbeu be be", "be our rum mbeu be be");
        breakDecrypt("sable ship dead blood", "sable ship dead blood");
        breakDecrypt(null, "abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc abc abc abc abc abc def def def def def def abc def");
        breakDecrypt(null, "be eb our uor ruo oru uro rou rum urm rmu mur umr mru");
        breakDecrypt("be our rum", "be our rum");
        breakDecrypt("be dead", "eb abca");
        breakDecrypt(null, "ef abca");
        breakDecrypt(null, "abcdefghijklmnopqrstuvwxyz");
    }
    public void breakDecrypt(String expect, String in){
        String[] input = in.split(" ");
        Infiltration.SubstitutionMap map = Infiltration.breakDecrypt(input);
        if (map == null)
            assertEquals(expect, map);
        else
            assertEquals(expect, map.decrypt(input));
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
    }

    private void mapKeyValidForWord(String encAdd, String decAdd, String encValid, String decValid){
        Infiltration.SubstitutionMap map = new Infiltration.SubstitutionMap();
        map.applywordToMap(encAdd, decAdd);
        assertEquals(encAdd.length(), map.size);
        assertFalse(map.keyValidForWord(encValid, decValid));
    }

    @Test
    public void mapCopy(){
        Infiltration.SubstitutionMap original = new Infiltration.SubstitutionMap();
        original.applywordToMap("abcd", "abcd");
        Infiltration.SubstitutionMap copy = original.copy();
        original.applywordToMap("abcd", "lmnk");

        assertEquals(4, copy.size);
        assertEquals(4, original.size);

        assertEquals('l', original.decodeMap[0]);
        assertEquals('m', original.decodeMap[1]);
        assertEquals('n', original.decodeMap[2]);
        assertEquals('k', original.decodeMap[3]);

        assertEquals('a', copy.decodeMap[0]);
        assertEquals('b', copy.decodeMap[1]);
        assertEquals('c', copy.decodeMap[2]);
        assertEquals('d', copy.decodeMap[3]);

        assertEquals('a', original.encodeMap['l' - 'a']);
        assertEquals('b', original.encodeMap['m' - 'a']);
        assertEquals('c', original.encodeMap['n' - 'a']);
        assertEquals('d', original.encodeMap['k' - 'a']);

        assertEquals('a', copy.encodeMap[0]);
        assertEquals('b', copy.encodeMap[1]);
        assertEquals('c', copy.encodeMap[2]);
        assertEquals('d', copy.encodeMap[3]);
    }
}
