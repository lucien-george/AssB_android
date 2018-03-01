package assb.ecse428.mcgill.ca.postalratecalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michael on 2/26/2018.
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

    @Test
    public void getVolumetricEquivalentRegularTest() throws Exception{

        float answer = 1;
        Calculator cal = new Calculator();
        assertEquals(cal.getVolumetricEquivalent("1","1","6000",Calculator.ShippingType.REGULAR), answer, 0.1);
    }

    @Test
    public void getVolumetricEquivalentXpressTest() throws Exception{

        float answer = 1;
        Calculator cal = new Calculator();
        assertEquals(cal.getVolumetricEquivalent("1","1","5000",Calculator.ShippingType.XPRESS), answer, 0.1);
    }

    @Test
    public void getVolumetricEquivalentPriorityTest() throws Exception{

        float answer = 1;
        Calculator cal = new Calculator();
        assertEquals(cal.getVolumetricEquivalent("1","1","5000",Calculator.ShippingType.PRIORITY), answer, 0.1);
    }

    @Test
    public void getPostalRateLetterMailTest1() throws Exception{

        //ShippingType -- Regular
        //LetterMail  --  Letter
        //Stamp -- Booklet

        Float answer = 0.85f;
        Calculator cal = new Calculator();
       assertEquals(
        cal.getPostalRateLetterMail( "1",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
        answer,
        0.1
        );


    }


}
