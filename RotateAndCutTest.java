import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ziver on 2016-10-11.
 */
public class RotateAndCutTest {

    @Test
    public void noChange(){
        RotateAndCut testObj = new RotateAndCut();
        assertEquals("ZZZ", testObj.calc(0, "ZZZ"));
        assertEquals("ZZZ", testObj.calc(1, "ZZZ"));
    }

    @Test
    public void simple(){
        RotateAndCut testObj = new RotateAndCut();
        assertEquals("ntToSleep", testObj.calc(1, "IWantToSleep"));
    }

    @Test
    public void advanced(){
        RotateAndCut testObj = new RotateAndCut();
        assertEquals("ntToSle", testObj.calc(2, "IWantToSleep"));
    }

    @Test
    public void nasty(){
        RotateAndCut testObj = new RotateAndCut();
        assertEquals("ToS", testObj.calc(1_000_000_000, "IWantToSleep"));
    }

    @Test
    public void maxInputLimit(){
        RotateAndCut testObj = new RotateAndCut();
        assertEquals("ToS", testObj.calc(1_000_000_000, "tToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleepIWantToSleep" +
                "IWantToSleepIWantToSleep"));
    }
}
