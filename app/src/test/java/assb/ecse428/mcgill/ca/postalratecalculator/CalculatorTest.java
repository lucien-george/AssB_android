package assb.ecse428.mcgill.ca.postalratecalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 2/26/2018.
 */

public class CalculatorTest {

    @Test
    public void getLetterMailTypeTest() throws Exception{

        Calculator cal = new Calculator() ;
        assertEquals(cal.getLetterMailType("200","100","3","10"), Calculator.LetterMail.LETTER);


    }


    @Test
    public void getLetterMailTypeOtherTest() throws Exception{

        Calculator cal = new Calculator();
        assertEquals(cal.getLetterMailType("300","200","15","10"), Calculator.LetterMail.OTHER);
    }

    @Test
    public void getLetterMailTypeErrorTest() throws Exception{

        Calculator cal = new Calculator();
        assertEquals(cal.getLetterMailType("1","1","15","10"), Calculator.LetterMail.ERROR);
    }


}
