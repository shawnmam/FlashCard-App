package com.flashcards.shawnsflashcards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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



    }


}
