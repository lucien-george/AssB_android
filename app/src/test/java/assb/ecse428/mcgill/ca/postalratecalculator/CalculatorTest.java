package assb.ecse428.mcgill.ca.postalratecalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Michael on 2/26/2018.
 */

public class CalculatorTest {

    /* Start of Valid test for getLetterMailType*/
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

    /* End of Valid test for getLetterMailType*/

      /*Start of Invalid test for getLetterMailType*/

    @Test
    public void getLetterMailTypeErrorTest1() throws Exception{

        Calculator cal = new Calculator();
        assertEquals(cal.getLetterMailType("0","0","0","0"), Calculator.LetterMail.ERROR);
    }

    @Test
    public void getLetterMailTypeErrorTest2() throws Exception{

        Calculator cal = new Calculator();
        assertEquals(cal.getLetterMailType("99999999","99999999","99999999","99999999"), Calculator.LetterMail.ERROR);
    }

    /*End of Invalid test for getLetterMailType*/

    /*Start of Valid test for getVolumetricEquivalentRegular*/
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
     /*End of Valid test for getVolumetricEquivalentRegular*/

     /*Start of Invalid test for getVolumetricEquivalentPriority*/
     @Test
     public void getVolumetricEquivalentErrorTest1() throws Exception{

         float answer = 0;
         Calculator cal = new Calculator();
         assertEquals(cal.getVolumetricEquivalent("0","0","0",Calculator.ShippingType.REGULAR), answer, 0.1);
     }

    @Test
    public void getVolumetricEquivalentErrorTest2() throws Exception{

        float answer = 0;
        Calculator cal = new Calculator();
        assertEquals(cal.getVolumetricEquivalent("0","0","0",Calculator.ShippingType.XPRESS), answer, 0.1);
    }

     /*End of Invalid test for getVolumetricEquivalentPriorityTest*/

    /*Start of Valid test for getPostalRateLetterMail*/

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

    @Test
    public void getPostalRateLetterMailTest18() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- Booklet
        // 30 <= Weight <= 50

        Float answer = 2.00f * 1.20f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest19() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- METER_POSTAL_INDICIA
        // Weight <= 30

        Float answer =  2.00f * 0.82f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "10",Calculator.LetterMail.LETTER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest20() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- METER_POSTAL_INDICIA
        // 30 <= Weight <= 50

        Float answer =  2.00f * 1.19f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest21() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- SINGLE_STAMP
        // Weight <= 30

        Float answer =  2.00f * 1.00f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "10",Calculator.LetterMail.LETTER ,Calculator.Stamp.SINGLE_STAMP,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest22() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- SINGLE_STAMP
        // 30 <= Weight <= 50

        Float answer =  2.00f * 1.20f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.SINGLE_STAMP,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest23() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 80

        Float answer =  2.00f * 1.80f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "80",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest24() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 150

        Float answer =  2.00f * 2.95f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "150",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest25() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 250

        Float answer =  2.00f * 4.10f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "250",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest26() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 350

        Float answer =  2.00f * 4.70f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "350",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest27() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 450

        Float answer =  2.00f * 5.05f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "450",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest28() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 80

        Float answer =  2.00f * 1.76f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "80",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest29() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 150

        Float answer =  2.00f * 2.85f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "150",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest30() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 250

        Float answer =  2.00f * 4.00f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "250",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest31() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 350

        Float answer =  2.00f * 4.54f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "350",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest32() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 450

        Float answer =  2.00f * 4.87f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "450",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }



///lfksehwelfksdlfk

    @Test
    public void getPostalRateLetterMailTest33() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight <= 30

        Float answer = 2.00f * 0.85f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }



    @Test
    public void getPostalRateLetterMailTest34() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- METER_POSTAL_INDICIA
        // Weight <= 30

        Float answer = 2.00f * 0.82f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "10",Calculator.LetterMail.LETTER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest35() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- METER_POSTAL_INDICIA
        // 30 <= Weight <= 50

        Float answer =   2.00f * 1.19f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }


    @Test
    public void getPostalRateLetterMailTest36() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- SINGLE_STAMP
        // Weight <= 30

        Float answer =  2.00f * 1.00f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "10",Calculator.LetterMail.LETTER ,Calculator.Stamp.SINGLE_STAMP,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest37() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- SINGLE_STAMP
        // 30 <= Weight <= 50

        Float answer =  2.00f * 1.20f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.SINGLE_STAMP,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest38() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 80

        Float answer =  3.00f * 1.80f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "80",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest39() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 150

        Float answer =  3.00f * 2.95f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "150",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest40() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 250

        Float answer = 3.00f * 4.10f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "250",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest41() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 350

        Float answer =  3.00f * 4.70f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "350",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest42() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- BOOKLET
        // Weight = 450

        Float answer = 3.00f * 5.05f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "450",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest43() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 80

        Float answer =  3.00f * 1.76f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "80",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest44() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 150

        Float answer =  3.00f * 2.85f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "150",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest45() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 250

        Float answer =  3.00f * 4.00f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "250",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest46() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 350

        Float answer =  3.00f * 4.54f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "350",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailTest47() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- METER_POSTAL_INDICIA
        // Weight = 450

        Float answer =  3.00f * 4.87f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "450",Calculator.LetterMail.OTHER ,Calculator.Stamp.METER_POSTAL_INDICIA,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }


    @Test
    public void getPostalRateLetterMailTest48() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- Booklet
        // 30 <= Weight <= 50

        Float answer =  2.00f * 1.20f;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "40",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    /*End of Valid test for getPostalRateLetterMail*/

    /*Start of Invalid test for checkPostalCode*/
    @Test
    public void getPostalRateLetterMailErrorTest1() throws Exception{

        //ShippingType -- Regular
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight = 100

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "100",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest2() throws Exception{

        //ShippingType -- Regular
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight = 0

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "0",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTes3() throws Exception{

        //ShippingType -- Regular
        //LetterMail  --  Other
        //Stamp -- Booklet
        //Weight = 1000

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1000",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest4() throws Exception{

        //ShippingType -- Regular
        //LetterMail  --  OTHER
        //Stamp -- Booklet
        //Weight = 0

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "0",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.REGULAR) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest5() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight = 100

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "100",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest6() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight = 0

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "0",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTes7() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  Other
        //Stamp -- Booklet
        //Weight = 1000

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1000",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest8() throws Exception{

        //ShippingType -- XPRESS
        //LetterMail  --  OTHER
        //Stamp -- Booklet
        //Weight = 0

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "0",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.XPRESS) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest9() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight = 100

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "100",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest10() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Letter
        //Stamp -- Booklet
        //Weight = 0

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "0",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTes11() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  Other
        //Stamp -- Booklet
        //Weight = 1000

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "1000",Calculator.LetterMail.OTHER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }

    @Test
    public void getPostalRateLetterMailErrorTest12() throws Exception{

        //ShippingType -- PRIORITY
        //LetterMail  --  OTHER
        //Stamp -- Booklet
        //Weight = 0

        Float answer = (float)0;
        Calculator cal = new Calculator();
        assertEquals(
                cal.getPostalRateLetterMail( "0",Calculator.LetterMail.LETTER ,Calculator.Stamp.BOOKLET,Calculator.ShippingType.PRIORITY) ,
                answer,
                0.1
        );


    }





    /*End of Invalid test for checkPostalCode*/

    /*Start of Valid test for checkPostalCode*/

    @Test
    public void checkPostalCodeTest1() throws Exception {

        boolean answer = true;
        Calculator cal = new Calculator();
        assertEquals(cal.checkPostalCode("H5G5K7"), answer);



    }

    @Test
    public void checkPostalCodeTest2() throws Exception {

        boolean answer = true;
        Calculator cal = new Calculator();
        assertEquals(cal.checkPostalCode("H5G 5K7"), answer);



    }


    /*End of Valid test for checkPostalCode*/


    @Test
    public void checkDimensionsTest() throws Exception {

        boolean answer = true;
        Calculator cal = new Calculator();
        assertEquals(cal.checkDimensions("10","12", "12"), answer);
    }

    @Test
    public void checkWeight() throws Exception {

        boolean answer = true;
        Calculator cal = new Calculator();
        assertEquals(cal.checkWeight("10"), answer);
    }


}
