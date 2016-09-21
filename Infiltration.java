import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;


public class Infiltration{

	
	public static final String[][] dictionary = new String[][]{
		{},{},
		{"be"},
		{"our", "rum"},
		{"will", "dead", "hook", "ship"},
		{"blood", "sable"},
		{"avenge", "parrot"},
		{"captain"},
		{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}
	};
	public static String[] input;
	public static int uniqueChars;
    public static HashSet<SubstitutionMap> cache;
	public static SubstitutionMap solution;


	public static void main(String[] args) throws IOException {
	    String[] msg = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
             String line = in.readLine();
            if (!line.isEmpty())
                msg = line.split(" ");
        }
        SubstitutionMap solution = breakDecrypt(msg);

		if (solution != null){
            System.out.println(solution.decrypt(input));
		} else {
			System.out.println("Impossible");
		}
	}

    public static final SubstitutionMap breakDecrypt(String[] input){
        if (input == null || input.length == 0)
            return null;
        try {
            solution = null;
            Infiltration.input = input;
			cache = new HashSet<>();

            uniqueChars = countUniqueChars();
            breakDecrypt(0, new SubstitutionMap());
        } catch (IllegalStateException e) {}
        return solution;
    }

	public static int countUniqueChars(){
		boolean[] charMap = new boolean[26];
        int count = 0;
		for(String word : input){
			for(int i=0; i<word.length(); ++i){
				if (!charMap[word.charAt(i) - 'a']){
				    ++count;
                    charMap[word.charAt(i) - 'a'] = true;
                }
			}
		}
		return count;
	}


	public static final void breakDecrypt(int inputIndex, SubstitutionMap map){
	    if (cache.contains(map)) // already checked?
	        return;
		if (inputIndex >= input.length) { // The end?
			if (map.size == uniqueChars){
			    if (solution != null && !solution.equals(map)) {
                    solution = null;
                    throw new IllegalStateException("Second solution found");
                }
				solution = map;
			}
			cache.add(map);
			return; 
		}

		for (String dictionaryWord : dictionary[input[inputIndex].length()]) {
			if (map.keyValidForWord(input[inputIndex], dictionaryWord)) {
				SubstitutionMap mapCopy = map.copy();
				mapCopy.applywordToMap(input[inputIndex], dictionaryWord);
				breakDecrypt(inputIndex+1, mapCopy);
			}
		}
		// skip word
		breakDecrypt(inputIndex+1, map);
	}




    public static class SubstitutionMap{
		private char[] decodeMap = new char[26]; // [enc] -> clear
		private char[] encodeMap = new char[26]; // [clear] -> enc
		private int size;
        private int hashCode = 0;


        // we are running on one thread so we might be able to save time by declaring the data as static
        static char[] tmpDecodeMap = new char[26];
        static char[] tmpEncodeMap = new char[26];
		public boolean keyValidForWord(String enc, String word){
            for (int i = 0; i < 26; i++) {
                tmpDecodeMap[i] = 0;
                tmpEncodeMap[i] = 0;
            }
            for (int i=0; i<enc.length(); ++i) {
				char c = decodeMap[enc.charAt(i) - 'a'];
				char d = encodeMap[word.charAt(i) - 'a'];
				char y = tmpDecodeMap[enc.charAt(i) - 'a'];
				char z = tmpEncodeMap[word.charAt(i) - 'a'];
				if (c != 0 && c != word.charAt(i)) // is there already a decoding mapping
					return false;
				else if (d != 0 && d != enc.charAt(i)) // is there already a encoding mapping
					return false;
                else if (y != 0 && y != word.charAt(i)) // do we have two enc letters pointing to same clear letter
                    return false;
                else if (z != 0 && z != enc.charAt(i)) // do we have two clear letters pointing to same enc letter
                    return false;
				tmpDecodeMap[enc.charAt(i) - 'a'] = word.charAt(i);
                tmpEncodeMap[word.charAt(i) - 'a'] = enc.charAt(i);
			}
			return true;
		}

		public void applywordToMap(String enc, String word){
			for (int i=0; i<enc.length(); ++i) {
				if (decodeMap[enc.charAt(i) - 'a'] == 0)
					++size;
				decodeMap[enc.charAt(i) - 'a'] = word.charAt(i);
				encodeMap[word.charAt(i) - 'a'] = enc.charAt(i);
			}
			hashCode = -1;
		}

		public SubstitutionMap copy(){
			SubstitutionMap copy = new SubstitutionMap();
			System.arraycopy(decodeMap, 0, copy.decodeMap, 0, decodeMap.length);
			System.arraycopy(encodeMap, 0, copy.encodeMap, 0, encodeMap.length);
			copy.size = size;
            copy.hashCode = hashCode;
			return copy;
		}

		public char encode(char raw){
		    return encodeMap[raw - 'a'];
        }
        public char decode(char enc){
            return decodeMap[enc - 'a'];
        }
        public int size(){
            return size;
        }

		public void print(){
			for (int i=0; i<decodeMap.length; ++i) {
				System.out.print((char)(i+97) +"="+decodeMap[i]+" ");
			}
			System.out.println();
		}

        public String decrypt(String[] encrypted){
            StringBuilder str = new StringBuilder(100);
			for (int k = 0; k < encrypted.length; k++) {
				String word = encrypted[k];
				if (k != 0)	str.append(' ');
				for (int i = 0; i < word.length(); ++i) {
					char c = decodeMap[word.charAt(i) - 'a'];
					str.append((c == 0 ? '?' : c));
				}
			}
			return str.toString();
        }


        public int hashCode(){
            if (hashCode < 0)
                hashCode = Arrays.hashCode(decodeMap);
            return hashCode;
        }
        public boolean equals(Object o){
            if (o instanceof SubstitutionMap)
                return Arrays.equals(decodeMap, ((SubstitutionMap) o).decodeMap);
            return false;
        }
	}
}