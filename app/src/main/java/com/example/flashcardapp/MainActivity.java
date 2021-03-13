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

public class MainActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                 flashcard_answer1.setVisibility(View.INVISIBLE);
                 flashcard_answer2.setVisibility(View.INVISIBLE);
                 flashcard_answer3.setVisibility(View.INVISIBLE);
             }
         });

         findViewById(R.id.toggle_choices_invisibility).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

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
        }
    }
        }
