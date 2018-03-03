package assb.ecse428.mcgill.ca.postalratecalculator;

import android.os.Bundle;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Michael on 3/2/2018.
 */

public class MainActivityTest {

    @Test
    public void check_inputsTest(){
        boolean answer = true;
        MainActivity mainActivity = new MainActivity();
        assertEquals(mainActivity.check_inputs("150" , "100" , "4" , "30" , "H3G2A5" , "A0A1B4") , answer);
    }

    @Test
    public void check_inputsTestFailedTo(){
        boolean answer = false;
        MainActivity mainActivity = new MainActivity();
        assertEquals(mainActivity.check_inputs("150" , "100" , "4" , "30" , "H3G2A5" , "QQ1B32") , answer);
    }

    @Test
    public void check_inputsTestFailedFrom(){
        boolean answer = false;
        MainActivity mainActivity = new MainActivity();
        assertEquals(mainActivity.check_inputs("150" , "100" , "4" , "30" , "11knsls" , "A0A1B4") , answer);
    }
}
