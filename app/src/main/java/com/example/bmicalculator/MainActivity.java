package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    android.widget.Button calculateBmiButton;

    private final int REQ_CODE = 100;
    // Mic
    Button micOfHeight, micOfWeight, micOfAge;
    // initially no mic is pressed
    Boolean isHeightMicPressed = false, isWeightMicPressed = false, isAgeMicPressed = false;

    TextView currentHeight;
    TextView currentWeight;
    TextView currentAge;
    ImageView  decrementWeight, incrementWeight, decrementAge, incrementAge;
    SeekBar seekBar;
    // focus and non-focus karne k liye dono relative layout ka kaam hai
    RelativeLayout maleLayout, femaleLayout;

    int intWeight = 55;
    int intAge = 22;

    // this will store the current value of seekbar
    int currentProgress;

    // this variable is actually used to paste the selected value of seekbar to the textView
    String intProgress = "170";

    // male or female selected by the user
    String typeOfUser = "0";

    // this variable is actually used to set selected weight by the user
    String weight2 = "55";

    // this variable is actually used to set selected age by the user
    String age2 = "22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        calculateBmiButton = findViewById(R.id.calculateBmiButtonId);

        micOfHeight = findViewById(R.id.micOfHeight);
        micOfWeight = findViewById(R.id.micOfWeight);
        micOfAge = findViewById(R.id.micOfAge);

        currentHeight = findViewById(R.id.currentHeightId);
        currentWeight = findViewById(R.id.currentWeightId);
        currentAge = findViewById(R.id.currentAgeId);

        decrementWeight = findViewById(R.id.decrementWeightId);
        incrementWeight = findViewById(R.id.incrementWeightId);

        decrementAge = findViewById(R.id.decrementAgeId);
        incrementAge = findViewById(R.id.incrementAgeId);

        seekBar = findViewById(R.id.seekBarId);

        maleLayout = findViewById(R.id.maleId);
        femaleLayout = findViewById(R.id.femaleId);

        // if user clicks on male Layout then make it fous or hightlight it
        maleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_focus));
                femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_not_focus));
                typeOfUser = "Male";
            }
        });

        // if user clicks on female Layout then make it fous or hightlight it
        femaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_focus));
                maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_not_focus));
                typeOfUser = "Female";
            }
        });

        seekBar.setMax(300);
        seekBar.setProgress(170);
        // when user interact with seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // main coding for seekBar goes here
                currentProgress = progress;
                // currentProgress is an integer value and hence can not be shown directly to textView :(
                // therefore we have used a String variable -> intProgress jisme default se 170 daala hai :)
                intProgress = String.valueOf(currentProgress);
                currentHeight.setText(intProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no use
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no use
            }
        });

        // *************************************************************************************************************
        // when user clicks on micOfHeight
        // *************************************************************************************************************
        micOfHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHeightMicPressed = true;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tell Your Height Properly");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    isHeightMicPressed = false;
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // *************************************************************************************************************
        // when user clicks on micOfWeight
        // *************************************************************************************************************
        micOfWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeightMicPressed = true;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tell Your Weight Properly");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    isWeightMicPressed = false;
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // *************************************************************************************************************
        // when user clicks on micOfAge
        // *************************************************************************************************************
        micOfAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAgeMicPressed = true;
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tell Your Age Properly");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    isAgeMicPressed = false;
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        incrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAge += 1;
                age2 = String.valueOf(intAge);
                currentAge.setText(age2);
            }
        });

        incrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight += 1;
                weight2 = String.valueOf(intWeight);
                currentWeight.setText(weight2);
            }
        });

        decrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAge -= 1;
                age2 = String.valueOf(intAge);
                currentAge.setText(age2);
            }
        });

        decrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight -= 1;
                weight2 = String.valueOf(intWeight);
                currentWeight.setText(weight2);
            }
        });

        calculateBmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // before sending the user to BmiActivity we have to make sure that user has selected
                // maleLayout or femaleLayout and some other conditions too :)

                // while comparing the values , make use of .equals() only and not this ==
                if(typeOfUser.equals("0")){
                    Toast.makeText(MainActivity.this, "Select Male or Female", Toast.LENGTH_SHORT).show();
                }
                else if(intProgress.equals("0")){
                    Toast.makeText(MainActivity.this, "Height can not be zero", Toast.LENGTH_SHORT).show();
                }
                else if(intAge == 0 || intAge < 0){
                    Toast.makeText(MainActivity.this, "Age has to be more than 0", Toast.LENGTH_SHORT).show();
                }
                else if(intWeight == 0 || intWeight < 0){
                    Toast.makeText(MainActivity.this, "Weight has to be more than 0", Toast.LENGTH_SHORT).show();
                }
                else{
                    // redirect the user to BmiActivity :)
                    Intent intent= new Intent(MainActivity.this, BmiActivity.class);
                    // before starting the BmiActivity we have to pass the required data to BmiActivity
                    // to calclulate BMI such as height, weight, age, gender :)

                    // next activity mai typeOfUser will be referenced by gender
                    intent.putExtra("gender", typeOfUser);
                    // intProgress String hai int nhi :) :) :)
                    intent.putExtra("height", intProgress);
                    // weight2 String hai
                    intent.putExtra("weight", weight2);
                    // age2 String hai
                    intent.putExtra("age", age2);

                    startActivity(intent);
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if (isHeightMicPressed) {
//                        currentHeight.setText((CharSequence) result.get(0));
                        String userVoiceInput = (String) result.get(0);
                        String numString = "";
                        for(int i=0; i<userVoiceInput.length(); i++){
                            if(userVoiceInput.charAt(i) >= '1' && userVoiceInput.charAt(i) <= '9'){
                                numString += userVoiceInput.charAt(i);
                            }
                        }
                        // agar numString empty nhi raha toh
                        if(!numString.equals("")) {
                            currentHeight.setText(numString);
                        }
                        else{
                            currentHeight.setText("170");
                        }
                        intProgress = currentHeight.getText().toString();
                        if (intProgress.charAt(0) == '-') {
                            Toast.makeText(getApplicationContext(), "Height can not be -ve", Toast.LENGTH_SHORT).show();
                            currentHeight.setText("170");
                            isHeightMicPressed = false;
                            return;
                        }
                        if (intProgress.equals("zero")) {
                            intProgress = "0";
                            currentHeight.setText((CharSequence) intProgress);
                        } else if (intProgress.charAt(0) >= '1' && intProgress.charAt(0) <= '9') {
                            int integerValueOfIntProgress = Integer.parseInt(intProgress);
                            seekBar.setProgress(integerValueOfIntProgress);
                        } else {
                            Toast.makeText(getApplicationContext(), "Height should be a number", Toast.LENGTH_SHORT).show();
                            currentHeight.setText("170");
                        }
                        isHeightMicPressed = false;
                    }
                    else if(isWeightMicPressed){
//                        currentWeight.setText((CharSequence) result.get(0));
                        String userVoiceInput = (String) result.get(0);
                        String numString = "";
                        for(int i=0; i<userVoiceInput.length(); i++){
                            if(userVoiceInput.charAt(i) >= '1' && userVoiceInput.charAt(i) <= '9'){
                                numString += userVoiceInput.charAt(i);
                            }
                        }
                        // agar numString empty nhi raha toh
                        if(!numString.equals("")) {
                            currentWeight.setText(numString);
                        }
                        else{
                            currentWeight.setText("55");
                        }
                        weight2 = currentWeight.getText().toString();
                        if (weight2.charAt(0) == '-') {
                            Toast.makeText(getApplicationContext(), "Weight can not be -ve", Toast.LENGTH_SHORT).show();
                            // setting it again to 55 by default
                            currentWeight.setText("55");
                            isWeightMicPressed = false;
                            return;
                        }
                        if (weight2.equals("zero")) {
                            weight2 = "0";
                            currentWeight.setText((CharSequence) weight2);
                            Toast.makeText(getApplicationContext(), "Weight can not be 0", Toast.LENGTH_SHORT).show();
                            isWeightMicPressed = false;
                            return;
                        }
                        else if(weight2.charAt(0) >= '1' && weight2.charAt(0) <= '9'){
                            intWeight = Integer.parseInt(currentWeight.getText().toString());
                            isWeightMicPressed = false;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Weight should be a number", Toast.LENGTH_SHORT).show();
                            currentWeight.setText("55");
                            isWeightMicPressed = false;
                        }
                    }
                    else{
                        // otherwise age mic hi press hua hoga
//                        currentAge.setText((CharSequence) result.get(0));
                        String userVoiceInput = (String) result.get(0);
                        String numString = "";
                        for(int i=0; i<userVoiceInput.length(); i++){
                            if(userVoiceInput.charAt(i) >= '1' && userVoiceInput.charAt(i) <= '9'){
                                numString += userVoiceInput.charAt(i);
                            }
                        }
                        if(!currentAge.equals("")) {
                            currentAge.setText(numString);
                        }
                        else{
                            currentAge.setText("20");
                        }

                        age2 = currentAge.getText().toString();

                        if (age2.charAt(0) == '-') {
                            Toast.makeText(getApplicationContext(), "Age can not be -ve", Toast.LENGTH_SHORT).show();
                            // setting it again to 20 by default
                            currentAge.setText("20");
                            isAgeMicPressed = false;
                            return;
                        }
                        else if(age2.charAt(0) >= '1' && age2.charAt(0) <= '9'){
                            intAge = Integer.parseInt(currentAge.getText().toString());
                            isAgeMicPressed = false;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Age should be a number", Toast.LENGTH_SHORT).show();
                            currentAge.setText("20");
                            isAgeMicPressed = false;
                        }
                    }
                }
                break;
            }
        }
    }




}