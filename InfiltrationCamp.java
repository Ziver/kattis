import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Coding Camp implementation of Infiltration Kattis problem
 * https://open.kattis.com/problems/infiltration
 */

class InfiltrationCamp {
    private static final int ALPHABET_SIZE = 26;
    private static final String[][] dictionary = {{}, {},
            {"be"},
            {"our", "rum"},
            {"will", "dead", "hook", "ship"},
            {"blood", "sable"},
            {"avenge", "parrot"},
            {"captain"}};



    public static void main(String... aaargh) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
            String cryptoStr = in.readLine();
            String result = InfiltrationCamp.breakDecrypt(cryptoStr);

            if (result != null)
                System.out.println(result);
            else
                System.out.println("Impossible");
        }
    }

    public static String breakDecrypt(String cryptoStr) {
        String[] crypto = cryptoStr.split("\\s");
        int uniqueLetters = getUniqueLetters(crypto);

        ArrayList<char[]> keyMap = createDecryptionMaps(crypto); // All keys for each possible word
        ArrayList<char[]> mergeMap = mergeKeys(keyMap); // Merge all keys together
        char[] key = findCorrectKey(mergeMap, uniqueLetters); // find the correct final key if it exists

        if (key != null)
            return decode(cryptoStr, key);
        return null;
    }

    private static char[] findCorrectKey(ArrayList<char[]> enMap, int uniqueLetters) {
        char[] solution = null;
        for (char[] possibleMap : enMap) {
            if (countLetters(possibleMap) != uniqueLetters)
                continue;
            if (solution != null && !Arrays.equals(solution, possibleMap))
                return null; // Second solution found
            solution = possibleMap;
        }
        return solution;
    }

    private static ArrayList<char[]> mergeKeys(ArrayList<char[]> keyMap) {
        ArrayList<char[]> keyChain = new ArrayList<>(keyMap);
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < keyChain.size(); i++) {
            char[] key1 = keyChain.get(i);
            for (char[] key2 : keyMap) {
                char[] result = joinKeys(key1, key2);
                if (result != null) {
                    Integer hash = Arrays.hashCode(result);//keyHash(result);
                    if (!hashSet.contains( hash )) {
                        hashSet.add(hash);
                        keyChain.add(result);
                    }
                }
            }
        }

        return keyChain;
    }

    private static char[] joinKeys(char[] key1, char[] key2) {
        boolean[] letterBitMap = new boolean[ALPHABET_SIZE];
        char[] result = new char[ALPHABET_SIZE];
        boolean key1Changed = false, key2Changed = false;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (key1[i] == key2[i])
                result[i] = key1[i];
            else if (key1[i] == 0) {
                result[i] = key2[i];
                key2Changed = true;
            } else if (key2[i] == 0) {
                result[i] = key1[i];
                key1Changed = true;
            } else
                return null;

            if (result[i] != 0) {
                if (letterBitMap[result[i] - 'a'])
                    return null;
                letterBitMap[result[i] - 'a'] = true;
            }
        }
        if (key1Changed && key2Changed)
            return result;
        else
            return null;
    }

    private static ArrayList<char[]> createDecryptionMaps(String[] crypto) {
        ArrayList<char[]> list = new ArrayList<>();
        //For each word in the crypto, create possible keys using every sme size word in the dictionary
        for (String cryptoWord : crypto) {
            if (cryptoWord.length() < dictionary.length) {
                nextDictWord:
                for (String dictWord : dictionary[cryptoWord.length()]) {
                    char[] potentialKey = new char[ALPHABET_SIZE];
                    for (int i = 0; i < cryptoWord.length(); i++) {
                        char cryptoLetter = cryptoWord.charAt(i);
                        int index = cryptoLetter - 'a';
                        if (potentialKey[index] == 0 || potentialKey[index] == dictWord.charAt(i)) {
                            potentialKey[index] = dictWord.charAt(i);
                        } else {
                            continue nextDictWord;
                        }
                    }
                    list.add(potentialKey);
                }
            }
        }
        return list;
    }

    private static int getUniqueLetters(String[] message) {
        boolean[] charMap = new boolean[26];
        int count = 0;
        for(String word : message){
            for(int i=0; i<word.length(); ++i){
                if (!charMap[word.charAt(i) - 'a']){
                    ++count;
                    charMap[word.charAt(i) - 'a'] = true;
                }
            }
        }
        return count;
    }

    private static int countLetters(char[] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) ++count;
        }
        return count;
    }

	private static String decode(String message, char[] key){
	    StringBuilder str = new StringBuilder(message.length());
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ')
                str.append(' ');
            else
                str.append(key[message.charAt(i) - 'a']);
        }
        return str.toString();
	}

    private void printMap(ArrayList<char[]> decryptionMap) {
        for (char[] carray : decryptionMap)
            System.out.println(Arrays.toString(carray));
    }
}