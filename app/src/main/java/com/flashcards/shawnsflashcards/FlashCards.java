package com.flashcards.shawnsflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class FlashCards extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    Flashcard cardToEdit;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_flash_cards);
            flashcardDatabase = new FlashcardDatabase(getApplicationContext());
            allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flash_card_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flash_card_answer)).setText(allFlashcards.get(0).getAnswer());
        }

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


        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;


                //Makes the question visible and then makes the answer invisible
                findViewById(R.id.flash_card_question).setVisibility(View.VISIBLE);
                findViewById(R.id.flash_card_answer).setVisibility(View.INVISIBLE);

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flash_card_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flash_card_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });


        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(FlashCards.this,AddCardActivity.class);

                myIntent.putExtra("FillQuestion", "What is Voldemort's Real Name?");
                myIntent.putExtra("FillAnswer", "Tim Riddle");


                FlashCards.this.startActivityForResult(myIntent,100);

            }
        });


        findViewById(R.id.edit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (Flashcard f : allFlashcards) {
                    if (f.getQuestion().equals(allFlashcards.get(currentCardDisplayedIndex).getQuestion())) {
                        cardToEdit = f;
                    }
                }

                Intent myIntent = new Intent(FlashCards.this,AddCardActivity.class);
                FlashCards.this.startActivityForResult(myIntent,200);



            }
        });



        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(allFlashcards.size()!= 0) {

                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flash_card_question)).getText().toString());

                    allFlashcards = flashcardDatabase.getAllCards();

                }

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

            flashcardDatabase.insertCard(new Flashcard(Question,Answer));
            allFlashcards = flashcardDatabase.getAllCards();

        }

        else if (requestCode == 200 && resultCode == RESULT_OK) {
            // grab the data passed from AddCardActivity
            // set the TextViews to show the EDITED question and answer

            String Question = data.getExtras().getString("Question"); // 'Question' needs to match the key we used when we put the string in the Intent
            String Answer = data.getExtras().getString("Answer"); // 'Answer' needs to match the key we used when we put the string in the Intent

            cardToEdit.setQuestion(Question);
            cardToEdit.setAnswer(Answer);

            flashcardDatabase.updateCard(cardToEdit);
        }

//        Snackbar.make(findViewById(R.id.flash_card_question),
//                "Here's your FlashCard",
//                Snackbar.LENGTH_SHORT)
//                .show();

        Toast.makeText(getApplicationContext(), "Finished Making Flash Card", Toast.LENGTH_SHORT).show();


    }




    // returns a random number between minNumber and maxNumber, inclusive.
    // for example, if i called getRandomNumber(1, 3), there's an equal chance of it returning either 1, 2, or 3.
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }


}
