package assb.ecse428.mcgill.ca.postalratecalculator;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;

import assb.ecse428.mcgill.ca.postalratecalculator.Calculator.*;

public class MainActivity extends AppCompatActivity {

    private EditText from_et;
    private EditText to_et;
    private EditText length_et;
    private EditText width_et;
    private EditText height_et;
    private EditText weight_et;
    private RadioGroup type_group;
    private RadioGroup stamp_group;
    private RadioButton regular_btn;
    private RadioButton xpress_btn;
    private RadioButton priority_btn;
    private RadioButton booklet;
    private RadioButton meter_postal;
    private RadioButton single_stamp;
    private TextView rate_tv;

    private int selected_type , selected_stamp , selected_package , selected_letterMail;
    private float price;
    private String str_rate;
    private ShippingType shippingType;
    private LetterMail letterMail;
    private Stamp stamp;
    private Calculator calculator;
    private ArrayList<RateReader> rateReaderArrayList;
    private ArrayList<CodeReader> codeReaderArrayList;
    private CodeReader codeReader;
    private RateReader rateReader;
    private Double rate;
    private String code;
    private float equivalence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVariables();
    }

    // setting up ui and variables
    public void setUpVariables() {
        calculator = new Calculator();
        from_et = (EditText) findViewById(R.id.from_postalcode);
        to_et = (EditText) findViewById(R.id.to_postalcode);
        length_et = (EditText) findViewById(R.id.length);
        width_et = (EditText) findViewById(R.id.width);
        height_et = (EditText) findViewById(R.id.height);
        weight_et = (EditText) findViewById(R.id.weight);
        type_group = (RadioGroup) findViewById(R.id.radiogroup);
        stamp_group = (RadioGroup) findViewById(R.id.stampgroup);
        meter_postal = (RadioButton) findViewById(R.id.meter_postal);
        single_stamp = (RadioButton) findViewById(R.id.single_stamp);
        booklet = (RadioButton) findViewById(R.id.booklet);
        regular_btn = (RadioButton) findViewById(R.id.regular);
        xpress_btn = (RadioButton) findViewById(R.id.xpress);
        priority_btn = (RadioButton) findViewById(R.id.priority);
        rate_tv = (TextView) findViewById(R.id.rate_tv);
        codeReader = new CodeReader();
        rateReader = new RateReader();
        codeReaderArrayList = new ArrayList<>();
        rateReaderArrayList = new ArrayList<>();

    }

    private void showAlertDialog(String title , String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public boolean check_inputs(String str_length , String str_width , String str_height , String str_weight, String str_from , String str_to) {
        boolean checked = false;
        calculator = new Calculator();
        if (calculator.checkDimensions(str_length , str_width , str_height) && calculator.checkWeight(str_weight) && calculator.checkPostalCode(str_from) && calculator.checkPostalCode(str_to)) {
            checked = true;
        }
        return checked;
    }

//    public boolean check_inputs() {
//        boolean checked = false;
//        String str_length = length_et.getText().toString();
//        String str_width = width_et.getText().toString();
//        String str_height = height_et.getText().toString();
//        String str_weight = weight_et.getText().toString();
//        String str_from = from_et.getText().toString();
//        String str_to = to_et.getText().toString();
//        if (calculator.checkDimensions(str_length , str_width , str_height) && calculator.checkWeight(str_weight) && calculator.checkPostalCode(str_from) && calculator.checkPostalCode(str_to)) {
//            checked = true;
//        }
//        return checked;
//    }

    // action handler for button click
    public void getRate(View view) {
        if (check_inputs(length_et.getText().toString() , width_et.getText().toString() , height_et.getText().toString() , weight_et.getText().toString() , from_et.getText().toString() , to_et.getText().toString())) {
            String str_length = length_et.getText().toString();
            String str_width = width_et.getText().toString();
            String str_height = height_et.getText().toString();
            String str_weight = weight_et.getText().toString();
            selected_type = type_group.getCheckedRadioButtonId();
            if(selected_type == regular_btn.getId()) {
                shippingType = ShippingType.REGULAR;
            }
            else if (selected_type == xpress_btn.getId()) {
                shippingType = ShippingType.XPRESS;
            }
            else {
                shippingType = ShippingType.PRIORITY;
            }
            selected_stamp = stamp_group.getCheckedRadioButtonId();
            if(selected_stamp == booklet.getId()) {
                stamp = Stamp.BOOKLET;
            }
            else if (selected_stamp == meter_postal.getId()) {
                stamp = Stamp.METER_POSTAL_INDICIA;
            }
            else {
                stamp = Stamp.SINGLE_STAMP;
            }
            letterMail = calculator.getLetterMailType(str_length , str_width , str_height , str_weight);
            readDataCode();
            str_rate = String.valueOf(new DecimalFormat("###.##").format(calculator.getPostalRateLetterMail(weight_et.getText().toString() , letterMail , stamp , shippingType) + readDataRate()));
            rate_tv.setText("Your rate is: $" + str_rate);
        }
        else {
            showAlertDialog("Error" , "Invalid input format. Please try again.");
        }
    }

    private Double readDataRate() {
        InputStream is = getResources().openRawResource(R.raw.rates);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        String line = "";
        try {
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                // split by commas
                String[] tokens = line.split(",");
                // read data
                for(int i = 0 ; i < tokens.length ; i++) {
                    if (tokens[i].length() > 0) {
                        rateReader.setVe(Double.parseDouble(tokens[0]));
                        rateReader.setA1(Double.parseDouble(tokens[1]));
                        rateReader.setA2(Double.parseDouble(tokens[2]));
                        rateReader.setA3(Double.parseDouble(tokens[3]));
                        rateReader.setA4(Double.parseDouble(tokens[4]));
                        rateReader.setA5(Double.parseDouble(tokens[5]));
                        rateReader.setA6(Double.parseDouble(tokens[6]));
                        rateReader.setB1(Double.parseDouble(tokens[7]));
                        rateReader.setB2(Double.parseDouble(tokens[8]));
                        rateReader.setB3(Double.parseDouble(tokens[9]));
                        rateReader.setB4(Double.parseDouble(tokens[10]));
                        rateReader.setB5(Double.parseDouble(tokens[11]));
                        rateReader.setC1(Double.parseDouble(tokens[12]));
                        rateReader.setC2(Double.parseDouble(tokens[13]));
                        rateReader.setC3(Double.parseDouble(tokens[14]));
                        rateReader.setC4(Double.parseDouble(tokens[15]));
                        rateReader.setC5(Double.parseDouble(tokens[16]));
                        rateReader.setD1(Double.parseDouble(tokens[17]));
                        rateReader.setD2(Double.parseDouble(tokens[18]));
                        rateReader.setD3(Double.parseDouble(tokens[19]));
                        rateReader.setD4(Double.parseDouble(tokens[20]));
                        rateReader.setD5(Double.parseDouble(tokens[21]));
                        rateReader.setD6(Double.parseDouble(tokens[22]));
                        rateReader.setD7(Double.parseDouble(tokens[23]));
                        rateReader.setE1(Double.parseDouble(tokens[24]));
                        rateReader.setE2(Double.parseDouble(tokens[25]));
                        rateReader.setE3(Double.parseDouble(tokens[26]));
                        rateReader.setE4(Double.parseDouble(tokens[27]));
                        rateReader.setE5(Double.parseDouble(tokens[28]));
                        rateReader.setF1(Double.parseDouble(tokens[29]));
                        rateReader.setF2(Double.parseDouble(tokens[30]));
                        rateReader.setF3(Double.parseDouble(tokens[31]));
                        rateReader.setF4(Double.parseDouble(tokens[32]));
                        rateReader.setF5(Double.parseDouble(tokens[33]));
                        rateReader.setF6(Double.parseDouble(tokens[34]));
                        rateReader.setG1(Double.parseDouble(tokens[35]));
                        rateReader.setG2(Double.parseDouble(tokens[36]));
                        rateReader.setG3(Double.parseDouble(tokens[37]));
                        rateReader.setG4(Double.parseDouble(tokens[38]));
                        rateReader.setH1(Double.parseDouble(tokens[39]));
                        rateReader.setH2(Double.parseDouble(tokens[40]));
                        rateReader.setH3(Double.parseDouble(tokens[41]));
                        rateReader.setH4(Double.parseDouble(tokens[42]));
                        rateReader.setH5(Double.parseDouble(tokens[43]));
                        rateReader.setJ1(Double.parseDouble(tokens[44]));
                        rateReader.setJ2(Double.parseDouble(tokens[45]));
                        rateReaderArrayList.add(rateReader);
                    }
                    else {
                        rateReader.setVe(0.0);
                        rateReader.setA1(0.0);
                        rateReader.setA2(0.0);
                        rateReader.setA3(0.0);
                        rateReader.setA4(0.0);
                        rateReader.setA5(0.0);
                        rateReader.setA6(0.0);
                        rateReader.setB1(0.0);
                        rateReader.setB2(0.0);
                        rateReader.setB3(0.0);
                        rateReader.setB4(0.0);
                        rateReader.setB5(0.0);
                        rateReader.setC1(0.0);
                        rateReader.setC2(0.0);
                        rateReader.setC3(0.0);
                        rateReader.setC4(0.0);
                        rateReader.setC5(0.0);
                        rateReader.setD1(0.0);
                        rateReader.setD2(0.0);
                        rateReader.setD3(0.0);
                        rateReader.setD4(0.0);
                        rateReader.setD5(0.0);
                        rateReader.setD6(0.0);
                        rateReader.setD7(0.0);
                        rateReader.setE1(0.0);
                        rateReader.setE2(0.0);
                        rateReader.setE3(0.0);
                        rateReader.setE4(0.0);
                        rateReader.setE5(0.0);
                        rateReader.setF1(0.0);
                        rateReader.setF2(0.0);
                        rateReader.setF3(0.0);
                        rateReader.setF4(0.0);
                        rateReader.setF5(0.0);
                        rateReader.setF6(0.0);
                        rateReader.setG1(0.0);
                        rateReader.setG2(0.0);
                        rateReader.setG3(0.0);
                        rateReader.setG4(0.0);
                        rateReader.setH1(0.0);
                        rateReader.setH2(0.0);
                        rateReader.setH3(0.0);
                        rateReader.setH4(0.0);
                        rateReader.setH5(0.0);
                        rateReader.setJ1(0.0);
                        rateReader.setJ2(0.0);
                        rateReaderArrayList.add(rateReader);
                    }
                }
                rate = getRate();
                if(rate != 0.0) {
                    break;
                }
            }
        } catch (IOException e) {
            Log.wtf("MyActivity" , "Error reading data file on line " + line , e);
            e.printStackTrace();
        }
        return rate;
    }

    private void readDataCode() {
        InputStream is = getResources().openRawResource(R.raw.source_destination);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is , Charset.forName("UTF-8")));
        String from = from_et.getText().toString();
        String to = to_et.getText().toString();
        String line = "";
        try {
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null) {
                // split by commas
                String[] tokens = line.split(",");
                // read data
                codeReader.setFrom(tokens[0]);
                codeReader.setTo(tokens[1]);
                codeReader.setCode(tokens[2]);
                codeReaderArrayList.add(codeReader);
                code = getCode(from , to);
                if(!code.equals("")) {
                    break;
                }
            }
        } catch (IOException e) {
            Log.wtf("MyActivity" , "Error reading data file on line " + line , e);
            e.printStackTrace();
        }
    }

    public Double getRate() {
        String length = length_et.getText().toString();
        String width = width_et.getText().toString();
        String height = height_et.getText().toString();
        String from = from_et.getText().toString();
        String to = to_et.getText().toString();
        rate = 0.0;
        equivalence = calculator.getVolumetricEquivalent(length , width , height , shippingType);
        Double ve = rateReader.getVe();
        if((equivalence >= ve - 0.25) && (equivalence < ve + 0.25)) {
            if(shippingType.equals(ShippingType.REGULAR)) {
                rate = mapCode(code);
            }
            else if (shippingType.equals(ShippingType.XPRESS)) {
                rate = 2.0 * mapCode(code);
            }
            else if (shippingType.equals(ShippingType.PRIORITY)) {
                rate = 3.0 * mapCode(code);
            }
        }
        else {
            if(shippingType.equals(ShippingType.REGULAR)) {
                rate = 15.0;
            }
            else if (shippingType.equals(ShippingType.XPRESS)) {
                rate = 30.0;
            }
            else if (shippingType.equals(ShippingType.PRIORITY)) {
                rate = 45.0;
            }
        }
        return rate;
    }

    public Double mapCode(String code) {
        if(code.equals("A1")) {
            return rateReader.getA1();
        }
        else if(code.equals("A2")) {
            return rateReader.getA2();
        }
        else if(code.equals("A3")) {
            return rateReader.getA3();
        }
        else if(code.equals("A4")) {
            return rateReader.getA4();
        }
        else if(code.equals("A5")) {
            return rateReader.getA5();
        }
        else if(code.equals("A6")) {
            return rateReader.getA6();
        }
        else if(code.equals("B1")) {
            return rateReader.getB1();
        }
        else if(code.equals("B2")) {
            return rateReader.getB2();
        }
        else if(code.equals("B3")) {
            return rateReader.getB3();
        }
        else if(code.equals("B4")) {
            return rateReader.getB4();
        }
        else if(code.equals("B5")) {
            return rateReader.getB5();
        }
        else if(code.equals("C1")) {
            return rateReader.getC1();
        }
        else if(code.equals("C2")) {
            return rateReader.getC2();
        }
        else if(code.equals("C3")) {
            return rateReader.getC3();
        }
        else if(code.equals("C4")) {
            return rateReader.getC4();
        }
        else if(code.equals("C5")) {
            return rateReader.getC5();
        }
        else if(code.equals("D1")) {
            return rateReader.getD1();
        }
        else if(code.equals("D2")) {
            return rateReader.getD2();
        }
        else if(code.equals("D3")) {
            return rateReader.getD3();
        }
        else if(code.equals("D4")) {
            return rateReader.getD4();
        }
        else if(code.equals("D5")) {
            return rateReader.getD5();
        }
        else if(code.equals("D6")) {
            return rateReader.getD6();
        }
        else if(code.equals("D7")) {
            return rateReader.getD7();
        }
        else if(code.equals("E1")) {
            return rateReader.getE1();
        }
        else if(code.equals("E2")) {
            return rateReader.getE3();
        }
        else if(code.equals("E3")) {
            return rateReader.getE3();
        }
        else if(code.equals("E4")) {
            return rateReader.getE4();
        }
        else if(code.equals("E5")) {
            return rateReader.getE5();
        }
        else if(code.equals("F1")) {
            return rateReader.getF1();
        }
        else if(code.equals("F2")) {
            return rateReader.getF2();
        }
        else if(code.equals("F3")) {
            return rateReader.getF3();
        }
        else if(code.equals("F4")) {
            return rateReader.getF4();
        }
        else if(code.equals("F5")) {
            return rateReader.getF5();
        }
        else if(code.equals("F6")) {
            return rateReader.getF6();
        }
        else if(code.equals("G1")) {
            return rateReader.getG1();
        }
        else if(code.equals("G2")) {
            return rateReader.getG2();
        }
        else if(code.equals("G3")) {
            return rateReader.getG3();
        }
        else if(code.equals("G4")) {
            return rateReader.getG4();
        }
        else if(code.equals("H1")) {
            return rateReader.getH1();
        }
        else if(code.equals("H2")) {
            return rateReader.getH2();
        }
        else if(code.equals("H3")) {
            return rateReader.getH3();
        }
        else if(code.equals("H4")) {
            return rateReader.getH4();
        }
        else if(code.equals("H5")) {
            return rateReader.getH5();
        }
        else if(code.equals("J1")) {
            return rateReader.getJ1();
        }
        else if(code.equals("J2")) {
            return rateReader.getJ2();
        }
        else {
            return  0.0;
        }
    }

    public String getCode(String from , String to) {
        char first_to = from.charAt(0);
        int length = codeReader.getFrom().length();
        for(int i = 0; i < length ; i++) {
            char at = codeReader.getFrom().charAt(i);
            String sub = to.substring(0 , 3);
            String data_to = codeReader.getTo();
            if ((Character.compare(first_to , at) == 0) && data_to.equals(sub)) {
                code = codeReader.getCode();
                break;
            }
            else {
                code = "H5";
            }
        }
        return code;
    }
}