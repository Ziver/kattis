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
    //public static HashSet<Integer> cache;
	public static SubstitutionMap solution;


	public static void main(String[] args) throws IOException {
	    String[] msg;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
             msg = in.readLine().split(" ");
        }
        SubstitutionMap solution = breakDecrypt(msg);

		if (solution != null){
            System.out.println(solution.decrypt(input));
			//solution.print();
		} else {
			System.out.println("Impossible");
		}
	}

    public static final SubstitutionMap breakDecrypt(String[] input){
        if (input.length == 0)
            return null;
        try {
            solution = null;
            Infiltration.input = input;

            uniqueChars = countUniqueChars();
            breakDecrypt(0, new SubstitutionMap());
        } catch (IllegalStateException e) {}
        return solution;
    }

	public static int countUniqueChars(){
		HashSet<Character> set = new HashSet<>();
		for(String word : input){
			for(int i=0; i<word.length(); ++i){
				set.add(word.charAt(i));
			}
		}
		return set.size();
	}


	public static final void breakDecrypt(int inputIndex, SubstitutionMap map){
		if (inputIndex >= input.length) { // The end?
			if (map.size == uniqueChars){
			    if (solution != null && !solution.equals(map)) {
                    solution = null;
                    throw new IllegalStateException("Second solution found");
                }
				solution = map;
			}
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
		public char[] decodeMap = new char[26]; // [enc] -> clear
		public char[] encodeMap = new char[26]; // [clear] -> enc
		public int size;


		public boolean keyValidForWord(String enc, String word){
			char[] tmpDecodeMap = new char[26];
			for (int i=0; i<enc.length(); ++i) {
				char c = decodeMap[enc.charAt(i) - 'a'];
				char d = encodeMap[word.charAt(i) - 'a'];
				char z = tmpDecodeMap[enc.charAt(i) - 'a'];
				if (c != 0 && c != word.charAt(i))
					return false;
				else if (z != 0 && z != word.charAt(i))
					return false;
				else if (d != 0 && d != enc.charAt(i))
					return false;
				tmpDecodeMap[enc.charAt(i) - 'a'] = word.charAt(i);
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
		}

		public SubstitutionMap copy(){
			SubstitutionMap copy = new SubstitutionMap();
			System.arraycopy(decodeMap, 0, copy.decodeMap, 0, decodeMap.length);
			System.arraycopy(encodeMap, 0, copy.encodeMap, 0, encodeMap.length);
			copy.size = size;
			return copy;
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

        public boolean equals(Object o){
            if (o instanceof SubstitutionMap)
                return Arrays.equals(encodeMap, ((SubstitutionMap) o).encodeMap);
            return false;
        }
	}
}