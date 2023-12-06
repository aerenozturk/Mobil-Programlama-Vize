package com.example.vize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertActivity extends AppCompatActivity {

    EditText decimalInput, byteInput, celsiusInput;
    Spinner decimalSpinner, byteSpinner;
    TextView decimalSonuc, byteSonuc, celsiusSonuc;
    RadioGroup celsiusRadioGroup;
    RadioButton fahrenheitButton, kelvinButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        decimalInput = findViewById(R.id.input_decimal);
        byteInput = findViewById(R.id.input_byte);
        celsiusInput = findViewById(R.id.input_celsius);

        decimalSpinner = findViewById(R.id.spinner_decimal);
        byteSpinner = findViewById(R.id.spinner_byte);

        decimalSonuc = findViewById(R.id.decimal_sonuc);
        byteSonuc = findViewById(R.id.byte_sonuc);
        celsiusSonuc = findViewById(R.id.celsius_sonuc);

        celsiusRadioGroup = findViewById(R.id.radioGroup);
        fahrenheitButton = findViewById(R.id.fahrenheit);
        kelvinButton = findViewById(R.id.kelvin);

        fahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kelvinButton.setChecked(false);
                convertToFahrenheit();

            }
        });
        kelvinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fahrenheitButton.setChecked(false);
                convertToKelvin();
            }
        });
        setupSpinners();
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> decimalAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.decimal_conversion_types,
                android.R.layout.simple_spinner_item
        );
        ArrayAdapter<CharSequence> megabyteAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.megabyte_conversion_types,
                android.R.layout.simple_spinner_item
        );
        decimalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        megabyteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        decimalSpinner.setAdapter(decimalAdapter);
        byteSpinner.setAdapter(megabyteAdapter);

        decimalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                performDecimalConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        byteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                performMegabyteConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void performDecimalConversion() {
        String inputValue = decimalInput.getText().toString();

        if(inputValue.isEmpty()) {
            decimalSonuc.setText("Lütfen Sayı giriniz!");
            return;
        }
        double input = Double.parseDouble(inputValue);
        String conversionType = decimalSpinner.getSelectedItem().toString();

        if(conversionType.equals("Binary")) {
            decimalSonuc.setText("Sonuç:" + Integer.toBinaryString((int) input));
        } else if (conversionType.equals("Octal")) {
            decimalSonuc.setText("Sonuç:" + Integer.toOctalString((int) input));
        }else if (conversionType.equals("Hexadecimal")) {
            decimalSonuc.setText("Sonuç:" + Integer.toHexString((int) input));
        }
    }
    private void performMegabyteConversion() {
        String inputValue = byteInput.getText().toString();

        if (inputValue.isEmpty()) {
            byteSonuc.setText("Lütfen Sayı giriniz!");
            return;
        }
        double input = Double.parseDouble(inputValue);

        String conversionType = byteSpinner.getSelectedItem().toString();

        if(conversionType.equals("Kilobyte")) {
            byteSonuc.setText("Sonuç:" + String.valueOf(input * 1024));
        }else if (conversionType.equals("Byte")) {
            byteSonuc.setText("Sonuç:" + String.valueOf(input * 1024 *1024));
        }else if (conversionType.equals("Kibibyte")) {
            byteSonuc.setText("Sonuç:" + String.valueOf(input * 8 * 1024));
        }else if (conversionType.equals("Bit")) {
            byteSonuc.setText("Sonuç:" + String.valueOf(input * 1024 * 8 * 1024));
        }
    }
    private void convertToFahrenheit() {
        try {
            double celsiusTemp = Double.parseDouble((celsiusInput.getText().toString()));

            double fahrenheitTemp = (celsiusTemp * 9 / 5) + 32;
            displayResult("Sonuç:" + fahrenheitTemp +"Fahrenheit");

        }catch (NumberFormatException e){
            showToast("Sonuç");}
    }

    private void convertToKelvin() {
        try {
            double celsiusTemp = Double.parseDouble((celsiusInput.getText().toString()));

            double KelvinTemp = celsiusTemp +273;
            displayResult("Sonuç:" + KelvinTemp +"Kelvin");
        }catch (NumberFormatException e){
            showToast("Sonuç");
        }
    }
    private void displayResult(String message) {
        celsiusSonuc.setText(message);
    }
    private  void showToast(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}