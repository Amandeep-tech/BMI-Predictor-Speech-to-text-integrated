package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    android.widget.Button reCalculateButton;

    TextView bmiDisplay, bmiCategory, gender;
    Intent intent;
    // to show multiple images based on the BMI calculated
    ImageView imageView;

    // this will be used to show actual bmi on screen
    String bmi;
    float calculatedBmi;
    String height, weight;
    float intHeight, intWeight;

    //  I would like to change the relative layout based on the BMI calculated
    RelativeLayout relativeLayoutBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        getSupportActionBar().hide();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        intent = getIntent();

        bmiDisplay = findViewById(R.id.bmiDisplayId);
        bmiCategory = findViewById(R.id.bmiCategoryId);
        gender = findViewById(R.id.genderDisplayId);
        relativeLayoutBackground = findViewById(R.id.contentLayoutId);
        imageView = findViewById(R.id.imageViewId);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        // to calculate BMI we have to convert height and weight to float
        intHeight = Float.parseFloat(height);
        intWeight = Float.parseFloat(weight);

        // converting intHeight from cm to m
        intHeight /= 100;

        // formula to calculate BMI
        // calculatedBmi ek Float value hai

        // say I know my height in terms of feet -> 4.8 feet
        // then convert it to cm to give input = 4.8 * 30 = __ cm
        calculatedBmi = intWeight/(intHeight*intHeight);

        // let us convert this answer to String to show it to screen
        // bmi ek String variable hai
        bmi = Float.toString(calculatedBmi);

        if(calculatedBmi < 16){
            bmiCategory.setText("You are so Thin :(");
            relativeLayoutBackground.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.crosss);
        }
        else if(calculatedBmi < 16.9 && calculatedBmi > 16){
            bmiCategory.setText("You are moderate Thin");
            relativeLayoutBackground.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else if(calculatedBmi < 18.4 && calculatedBmi > 17){
            bmiCategory.setText("You have mild Thinness");
            relativeLayoutBackground.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else if(calculatedBmi < 25 && calculatedBmi > 18.4){
            bmiCategory.setText("Everything is normal :)");
//            relativeLayoutBackground.setBackgroundColor(Color.YELLOW);.
            imageView.setImageResource(R.drawable.ok);
        }
        else if(calculatedBmi < 29.4 && calculatedBmi > 25){
            bmiCategory.setText("You are over weight :(");
            relativeLayoutBackground.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else{
            bmiCategory.setText("You are under Obese class - 1");
            relativeLayoutBackground.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        // showing the calculated bmi now
        bmiDisplay.setText(bmi);

        // showing gender
        gender.setText(intent.getStringExtra("gender"));

        reCalculateButton = findViewById(R.id.reCalculateBmiButtonId);
        reCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect the user to MainActivity again
                Intent intent = new Intent(BmiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}