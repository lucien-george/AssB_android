package assb.ecse428.mcgill.ca.postalratecalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    private RadioGroup package_group;
    private RadioButton letter_btn;
    private RadioButton parcel_btn;
    private RadioButton regular_btn;
    private RadioButton xpress_btn;
    private RadioButton priority_btn;
    private RadioButton booklet;
    private RadioButton meter_postal;
    private RadioButton single_stamp;
    private TextView rate;

    private int selected_type , selected_stamp , selected_package , selected_letterMail;
    private String str_rate;
    private ShippingType shippingType;
    private LetterMail letterMail;
    private Stamp stamp;
    private PackageType packageType;
    private Calculator calculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVariables();
    }

    // setting up ui and variables
    private void setUpVariables() {
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
        package_group = (RadioGroup) findViewById(R.id.package_type);
        letter_btn = (RadioButton) findViewById(R.id.letter_rb);
        parcel_btn = (RadioButton) findViewById(R.id.parcel_rb);
        rate = (TextView) findViewById(R.id.rate_tv);

    }

    // action handler for button click
    public void getRate(View view) {
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
        selected_package = package_group.getCheckedRadioButtonId();
        if(selected_package == letter_btn.getId()) {
            packageType = PackageType.LETTERMAIL;
            letterMail = calculator.getLetterMailType(length_et.getText().toString() , width_et.getText().toString() , height_et.getText().toString() , weight_et.getText().toString());
            if(letterMail.equals(LetterMail.ERROR)) {
                showAlertDialog("Error" , "Dimension parameters do not match neither LETTER nor OTHER");
            }
        }
        else {
            packageType = PackageType.PARCEL;
        }
//        str_rate = String.valueOf(calculator.getPostalRate(weight_et.getText().toString() , letterMail , stamp));
        rate.setText("Your rate is: $" + str_rate);

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
}
