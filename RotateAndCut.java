import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ziver on 2016-10-11.
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
        int start = 0;
        int end = originalMsg.length();
        int currentLength = end;
        boolean cutInFront = true;
        while (cuts > 0 && currentLength > 3){
            currentLength = end - start;
            if (cutInFront)
                start += currentLength / 4;
            else
                end -= currentLength / 4;
            --cuts;
            cutInFront = !cutInFront;
        }
        return originalMsg.substring(start, end);
    }
}
