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
        //Weight <= 30

        Float answer = 0.85f;
        Calculator cal = new Calculator();
       assertEquals(
        cal.getPostalRateLetterMail( "1",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
        answer,
        0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest2() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  LETTER
        //Stamp -- Booklet
        //30 <= Weight <= 50 ,

        Float answer = 1.20f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest3() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  LETTER
        //Stamp --  METER_POSTAL_INDICIA
        //Weight <= 30


        Float answer = 0.82f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1",Calculator.LetterMail.LETTER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest4() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  LETTER
        //Stamp --  METER_POSTAL_INDICIA
        // 30 <= Weight <= 50


        Float answer = 1.19f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest5() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  LETTER
        //Stamp --  SINGLE_STAMP
        // Weight <= 30


        Float answer = 1.00f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1",Calculator.LetterMail.LETTER ,Calculator.Stamp.SINGLE_STAMP,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest6() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  LETTER
        //Stamp --  SINGLE_STAMP
        // 30 <= Weight <= 50


        Float answer = 1.20f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.SINGLE_STAMP,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest7() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        //Weight = 80


        Float answer = 1.80f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "80",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest8() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        //Weight = 150


        Float answer = 2.95f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "150",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest9() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        //Weight = 250


        Float answer = 4.10f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "250",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest10() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        //Weight = 350


        Float answer = 4.70f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "350",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest11() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        //Weight = 250


        Float answer = 5.05f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "450",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest12() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        //Weight = 80


        Float answer = 1.76f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "80",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest13() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        //Weight = 150


        Float answer = 2.85f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "150",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest14() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        //Weight = 250


        Float answer = 4.00f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "250",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest15() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        //Weight = 350


        Float answer = 4.54f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "350",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest16() throws Exception{

        //ShippingType -- REGULAR
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        //Weight = 450


        Float answer = 4.87f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "450",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest17() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight <= 30

        Float answer = 2.00f * 0.85f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }










}
