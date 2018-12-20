package com.flashcards.shawnsflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCards extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_flash_cards);


        // Makes the answer invisible in the beginning
        findViewById(R.id.flash_card_answer).setVisibility(View.INVISIBLE);


        findViewById(R.id.flash_card_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Makes the question invisible and then makes the answer visible
                findViewById(R.id.flash_card_question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flash_card_answer).setVisibility(View.VISIBLE);

            }
        });


        findViewById(R.id.flash_card_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Makes the question visible and then makes the answer invisible
                findViewById(R.id.flash_card_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flash_card_answer).setVisibility(View.INVISIBLE);

            }
        });


        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(FlashCards.this,AddCardActivity.class);

                myIntent.putExtra("FillQuestion", "What is Voldemort's Real Name?");
                myIntent.putExtra("FillAnswer", "Tim Riddle");


                FlashCards.this.startActivityForResult(myIntent,100);

            }
        });





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        findViewById(R.id.flash_card_question).setVisibility(View.VISIBLE);
        findViewById(R.id.flash_card_answer).setVisibility(View.INVISIBLE);

        if ((requestCode == 100) && (resultCode == RESULT_OK)) { // this 100 needs to match the 100 we used when we called startActivityForResult!

            String Question = data.getExtras().getString("Question"); // 'Question' needs to match the key we used when we put the string in the Intent
            String Answer = data.getExtras().getString("Answer"); // 'Answer' needs to match the key we used when we put the string in the Intent

            ((TextView) findViewById(R.id.flash_card_question)).setText(Question);
            ((TextView) findViewById(R.id.flash_card_answer)).setText(Answer);



        }

//        Snackbar.make(findViewById(R.id.flash_card_question),
//                "Here's your FlashCard",
//                Snackbar.LENGTH_SHORT)
//                .show();

        Toast.makeText(getApplicationContext(), "Finished Making Flash Card", Toast.LENGTH_SHORT).show();


    }


}
