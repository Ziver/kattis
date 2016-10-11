import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Coding Camp on 2016-10-11.
 */
public class RotateAndCut {

    public static void main(String[] args) throws IOException {
        RotateAndCut rotateAndCut = new RotateAndCut();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(in.readLine());
        for (int i = 0; i <count; i++) {
            char c;
            int cuts = 0;
            while ((c = (char) in.read()) != ' ')
               cuts = cuts * 10 + c-'0';

            System.out.println(
                    rotateAndCut.calc(
                            cuts,
                            in.readLine()));
        }
        in.close();
    }


    public String calc(int cuts, String originalMsg) {
        return calc(cuts, originalMsg, true);
    }
    public String calc(int cuts, String originalMsg, boolean cutInFront) {
        if (cuts == 0)
            return originalMsg;
        int charToCut = originalMsg.length() / 4;
        if (charToCut == 0)
            return originalMsg;

        if (cutInFront)
            return calc(
                    cuts-1,
                    originalMsg.substring(charToCut),
                    !cutInFront);
        else
            return calc(
                    cuts-1,
                    originalMsg.substring(0, originalMsg.length()-charToCut),
                    !cutInFront);
    }
}
