package com.example.priyankatummala.mathquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.priyankatummala.MathQuiz.CALLINGOP";

    private Button mAddButton;
    private Button mSubtractButton;
    private Button mMultiplyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddButton = (Button) findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, quizScreen.class);
                String message = "+";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        mSubtractButton = (Button) findViewById(R.id.subtract_button);
        mSubtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, quizScreen.class);
                String message = "-";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        mMultiplyButton = (Button) findViewById(R.id.multiply_button);
        mMultiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, quizScreen.class);
                String message = "*";
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });


    }

}
