package assb.ecse428.mcgill.ca.postalratecalculator;

/**
 * Created by lucien on 2018-02-25.
 */

public class Calculator {

    private LetterMail letterMail;
    private Stamp stamp;
    private ShippingType shippingType;
    private float rate;
    private float equivalence;
    private RateReader rateReader;

    public Calculator() {
    }

    public enum ShippingType {
        REGULAR , XPRESS , PRIORITY
    }

    public enum LetterMail {
        LETTER , OTHER , ERROR
    }

    public enum Stamp {
        BOOKLET , METER_POSTAL_INDICIA , SINGLE_STAMP
    }
    public LetterMail getLetterMailType(String length , String width , String height , String weight) {
        int length_int = Integer.parseInt(length);
        int width_int = Integer.parseInt(width);
        int height_int = Integer.parseInt(height);
        int weight_int = Integer.parseInt(weight);
        if ((length_int >= 140 && length_int <= 245)
                && (width_int >= 90 && width_int <= 156)
                && (height_int >= 0.18 && height_int <= 5)
                && (weight_int >= 2 && weight_int <= 50)) {
            letterMail = LetterMail.LETTER;
        }
        else if ((length_int >= 140 && length_int <= 380)
                && (width_int >= 90 && width_int <= 270)
                && (height_int >= 0.18 && height_int <= 20)
                && (weight_int >= 3 && weight_int <= 500)) {
            letterMail = LetterMail.OTHER;
        }
        else {
            letterMail = LetterMail.ERROR;
        }
        return letterMail;
    }

    public float getVolumetricEquivalent(String length , String width , String height , ShippingType shippingType) {
        float length_fl = Integer.parseInt(length);
        float width_fl = Integer.parseInt(width);
        float height_fl = Integer.parseInt(height);
        float volumetricEquivalent = 0;
        if(shippingType.equals(ShippingType.REGULAR)) {
            volumetricEquivalent = ((length_fl * width_fl * height_fl) / 6000); // 6000 is the density factor
            return volumetricEquivalent;
        }
        else {
            volumetricEquivalent = ((length_fl * width_fl * height_fl) / 5000); // 5000 is the density factor
            return volumetricEquivalent;
        }
    }

    public float getPostalRateLetterMail(String weight , LetterMail letterMail , Stamp stamp , ShippingType shippingType){
        int weight_int = Integer.parseInt(weight);
        if(shippingType.equals(ShippingType.REGULAR)) {
            if (letterMail.equals(LetterMail.LETTER)) {
                if(stamp.equals(Stamp.BOOKLET)) {
                    if (weight_int <= 30) {
                        rate = 0.85f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 1.20f;
                    }
                }
                else if (stamp.equals(Stamp.METER_POSTAL_INDICIA)) {
                    if (weight_int <= 30) {
                        rate = 0.82f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 1.19f;
                    }
                }
                else if (stamp.equals(Stamp.SINGLE_STAMP)) {
                    if (weight_int <= 30) {
                        rate = 1.00f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 1.20f;
                    }
                }
            }
            else if (letterMail.equals(LetterMail.OTHER)) {
                if(stamp.equals(Stamp.BOOKLET) || stamp.equals(Stamp.SINGLE_STAMP)) {
                    if (weight_int <= 100) {
                        rate = 1.80f;
                    }
                    else if(weight_int > 100 && weight_int <= 200) {
                        rate = 2.95f;
                    }
                    else if(weight_int > 200 && weight_int <= 300) {
                        rate = 4.10f;
                    }
                    else if(weight_int > 300 && weight_int <= 400) {
                        rate = 4.70f;
                    }
                    else if(weight_int > 400 && weight_int <= 500) {
                        rate = 5.05f;
                    }
                }
                else if(stamp.equals(Stamp.METER_POSTAL_INDICIA)) {
                    if (weight_int <= 100) {
                        rate = 1.76f;
                    }
                    else if(weight_int > 100 && weight_int <= 200) {
                        rate = 2.85f;
                    }
                    else if(weight_int > 200 && weight_int <= 300) {
                        rate = 4.00f;
                    }
                    else if(weight_int > 300 && weight_int <= 400) {
                        rate = 4.54f;
                    }
                    else if(weight_int > 400 && weight_int <= 500) {
                        rate = 4.87f;
                    }
                }
            }
        }
        else if (shippingType.equals(ShippingType.XPRESS)) {
            if (letterMail.equals(LetterMail.LETTER)) {
                if(stamp.equals(Stamp.BOOKLET)) {
                    if (weight_int <= 30) {
                        rate = 2.00f * 0.85f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 2.00f * 1.20f;
                    }
                }
                else if (stamp.equals(Stamp.METER_POSTAL_INDICIA)) {
                    if (weight_int <= 30) {
                        rate = 2.00f * 0.82f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 2.00f * 1.19f;
                    }
                }
                else if (stamp.equals(Stamp.SINGLE_STAMP)) {
                    if (weight_int <= 30) {
                        rate = 2.00f * 1.00f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 2.00f * 1.20f;
                    }
                }
            }
            else if (letterMail.equals(LetterMail.OTHER)) {
                if(stamp.equals(Stamp.BOOKLET) || stamp.equals(Stamp.SINGLE_STAMP)) {
                    if (weight_int <= 100) {
                        rate = 2.00f * 1.80f;
                    }
                    else if(weight_int > 100 && weight_int <= 200) {
                        rate = 2.00f * 2.95f;
                    }
                    else if(weight_int > 200 && weight_int <= 300) {
                        rate = 2.00f * 4.10f;
                    }
                    else if(weight_int > 300 && weight_int <= 400) {
                        rate = 2.00f * 4.70f;
                    }
                    else if(weight_int > 400 && weight_int <= 500) {
                        rate = 2.00f * 5.05f;
                    }
                }
                else if(stamp.equals(Stamp.METER_POSTAL_INDICIA)) {
                    if (weight_int <= 100) {
                        rate = 2.00f * 1.76f;
                    }
                    else if(weight_int > 100 && weight_int <= 200) {
                        rate =  2.00f * 2.85f;
                    }
                    else if(weight_int > 200 && weight_int <= 300) {
                        rate =  2.00f * 4.00f;
                    }
                    else if(weight_int > 300 && weight_int <= 400) {
                        rate = 2.00f * 4.54f;
                    }
                    else if(weight_int > 400 && weight_int <= 500) {
                        rate = 2.00f * 4.87f;
                    }
                }
            }
        }
        else if (shippingType.equals(ShippingType.PRIORITY)) {
            if (letterMail.equals(LetterMail.LETTER)) {
                if(stamp.equals(Stamp.BOOKLET)) {
                    if (weight_int <= 30) {
                        rate = 2.00f * 0.85f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 2.00f * 1.20f;
                    }
                }
                else if (stamp.equals(Stamp.METER_POSTAL_INDICIA)) {
                    if (weight_int <= 30) {
                        rate = 2.00f * 0.82f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 2.00f * 1.19f;
                    }
                }
                else if (stamp.equals(Stamp.SINGLE_STAMP)) {
                    if (weight_int <= 30) {
                        rate = 2.00f * 1.00f;
                    }
                    else if(weight_int > 30 && weight_int <= 50) {
                        rate = 2.00f * 1.20f;
                    }
                }
            }
            else if (letterMail.equals(LetterMail.OTHER)) {
                if(stamp.equals(Stamp.BOOKLET) || stamp.equals(Stamp.SINGLE_STAMP)) {
                    if (weight_int <= 100) {
                        rate = 3.00f * 1.80f;
                    }
                    else if(weight_int > 100 && weight_int <= 200) {
                        rate = 3.00f * 2.95f;
                    }
                    else if(weight_int > 200 && weight_int <= 300) {
                        rate = 3.00f * 4.10f;
                    }
                    else if(weight_int > 300 && weight_int <= 400) {
                        rate = 3.00f * 4.70f;
                    }
                    else if(weight_int > 400 && weight_int <= 500) {
                        rate = 3.00f * 5.05f;
                    }
                }
                else if(stamp.equals(Stamp.METER_POSTAL_INDICIA)) {
                    if (weight_int <= 100) {
                        rate = 3.00f * 1.76f;
                    }
                    else if(weight_int > 100 && weight_int <= 200) {
                        rate =  3.00f * 2.85f;
                    }
                    else if(weight_int > 200 && weight_int <= 300) {
                        rate =  3.00f * 4.00f;
                    }
                    else if(weight_int > 300 && weight_int <= 400) {
                        rate = 3.00f * 4.54f;
                    }
                    else if(weight_int > 400 && weight_int <= 500) {
                        rate = 3.00f * 4.87f;
                    }
                }
            }
        }
        return rate;
    }

    // check postal code format
    public boolean checkPostalCode(String postalCode) {
        boolean postal_code_checked = false;
        if(postalCode.length() == 6) {
            char first_letter = postalCode.charAt(0);
            char first_digit = postalCode.charAt(1);
            char second_letter = postalCode.charAt(2);
            char second_digit = postalCode.charAt(3);
            char third_letter = postalCode.charAt(4);
            char third_digit = postalCode.charAt(5);
            if(Character.isLetter(first_letter) && Character.isDigit(first_digit) && Character.isLetter(second_letter) && Character.isDigit(second_digit) && Character.isLetter(third_letter) && Character.isDigit(third_digit)) {
                postal_code_checked = true;
            }
        }
        else if (postalCode.length() == 7) {
            char first_letter = postalCode.charAt(0);
            char first_digit = postalCode.charAt(1);
            char second_letter = postalCode.charAt(2);
            char space = postalCode.charAt(3);
            char second_digit = postalCode.charAt(4);
            char third_letter = postalCode.charAt(5);
            char third_digit = postalCode.charAt(6);
            if(Character.isLetter(first_letter) && Character.isDigit(first_digit) && Character.isLetter(second_letter) && space == ' ' && Character.isDigit(second_digit) && Character.isLetter(third_letter) && Character.isDigit(third_digit)) {
                postal_code_checked = true;
            }
        }
        return postal_code_checked;
    }

    public boolean checkDimensions(String length , String width , String height) {
        boolean dimensions_checked = false;
        double dbl_length = 0.0 , dbl_width = 0.0 , dbl_height = 0.0;
        try {
            dbl_length = Double.parseDouble(length);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            dbl_width = Double.parseDouble(width);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            dbl_height = Double.parseDouble(height);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dimensions_checked = true;
        return dimensions_checked;
    }

    public boolean checkWeight(String weight) {
        boolean weight_checked = false;
        double dbl_weight = 0.0;
        try {
            dbl_weight = Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        weight_checked = true;
        return weight_checked;
    }
}