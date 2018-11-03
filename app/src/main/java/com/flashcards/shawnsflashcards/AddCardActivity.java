package com.flashcards.shawnsflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        String FillQuestion = getIntent().getStringExtra("FillQuestion"); // this string will be 'What is Voldemort's Real Name?`
        String FillAnswer = getIntent().getStringExtra("FillAnswer"); // this string will be 'Tim Riddle'



        findViewById(R.id.ExitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        findViewById(R.id.SubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String Question = ((EditText) findViewById(R.id.QuestionText)).getText().toString();
               String Answer =  ((EditText) findViewById(R.id.AnswerText)).getText().toString();

                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("Question", Question); // puts one string into the Intent, with the key as 'Question'
                data.putExtra("Answer", Answer); // puts another string into the Intent, with the key as 'Answer'
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });


    }
}
