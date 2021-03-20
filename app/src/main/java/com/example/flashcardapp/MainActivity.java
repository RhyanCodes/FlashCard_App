package com.example.flashcardapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.red, null));
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.green, null));

            }
        });

        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.green, null));
            }

        });
        findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.red, null));
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        TextView flashcard_Question = findViewById(R.id.flashcard_question);
        TextView flashcard_answer = findViewById(R.id.flashcard_answer);
        TextView flashcard_answer1 = findViewById(R.id.flashcard_answer1);
        TextView flashcard_answer2 = findViewById(R.id.flashcard_answer2);
        TextView flashcard_answer3 = findViewById(R.id.flashcard_answer3);
        ImageView toggle_choices_visibility = findViewById(R.id.toggle_choices_visibility);
        ImageView toggle_choices_invisibility = findViewById(R.id.toggle_choices_invisibility);

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show answer when tapped on
                flashcard_Question.setVisibility(View.INVISIBLE);
                flashcard_answer.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.orange, null));
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.orange, null));
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.orange, null));
                flashcard_Question.setVisibility(View.VISIBLE);
                flashcard_answer.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle_choices_visibility.setVisibility(View.INVISIBLE);
                toggle_choices_invisibility.setVisibility(View.VISIBLE);
                flashcard_answer1.setVisibility(View.INVISIBLE);
                flashcard_answer2.setVisibility(View.INVISIBLE);
                flashcard_answer3.setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.toggle_choices_invisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle_choices_invisibility.setVisibility(View.INVISIBLE);
                toggle_choices_visibility.setVisibility(View.VISIBLE);
                flashcard_answer1.setVisibility(View.VISIBLE);
                flashcard_answer2.setVisibility(View.VISIBLE);
                flashcard_answer3.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.add_flashcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.edit_flashcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                String Question = ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                String Answer1 = ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString();
                String Answer2 = ((TextView) findViewById(R.id.flashcard_answer2)).getText().toString();
                String Answer3 = ((TextView) findViewById(R.id.flashcard_answer3)).getText().toString();
                intent.putExtra("string1", Question);
                intent.putExtra("string2", Answer1);
                intent.putExtra("string3", Answer2);
                intent.putExtra("string4", Answer3);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(0).getAnswer());
        }

        final int[] currentCardDisplayedIndex = {0};

        findViewById(R.id.next_flashcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allFlashcards.size() == 0)
                    return;
                currentCardDisplayedIndex[0]++;
                if (currentCardDisplayedIndex[0] >= allFlashcards.size()) {
                    Snackbar.make(flashcard_Question,
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex[0] = 0;
                }
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex[0]);
                ((TextView) findViewById(R.id.flashcard_question)).setText(flashcard.getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer1)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.flashcard_answer2)).setText(flashcard.getWrongAnswer1());
                ((TextView) findViewById(R.id.flashcard_answer3)).setText(flashcard.getWrongAnswer2());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");
            String string3 = data.getExtras().getString("string3");
            String string4 = data.getExtras().getString("string4");
            ((TextView) findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(string2);
            ((TextView) findViewById(R.id.flashcard_answer2)).setText(string3);
            ((TextView) findViewById(R.id.flashcard_answer3)).setText(string4);
            Snackbar.make(findViewById(R.id.flashcard_answer),
                    "Flashcard saved successfully",
                    Snackbar.LENGTH_SHORT).show();
            flashcardDatabase.insertCard(new Flashcard(string1, string2, string3, string4));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
}
