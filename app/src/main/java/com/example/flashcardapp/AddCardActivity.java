package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        String Question = getIntent().getStringExtra("string1");
        String Answer1 = getIntent().getStringExtra("string2");
        String Answer2 = getIntent().getStringExtra("string3");
        String Answer3 = getIntent().getStringExtra("string4");
        ((TextView) findViewById(R.id.EnterQuestion)).setText(Question);
        ((TextView) findViewById(R.id.EnterAnswer1)).setText(Answer1);
        ((TextView) findViewById(R.id.EnterAnswer2)).setText(Answer2);
        ((TextView) findViewById(R.id.EnterAnswer3)).setText(Answer3);

        findViewById(R.id.CancelAddCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.SaveCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Question = ((EditText) findViewById(R.id.EnterQuestion)).getText().toString();
                String Answer1 = ((EditText) findViewById(R.id.EnterAnswer1)).getText().toString();
                String Answer2 = ((EditText) findViewById(R.id.EnterAnswer2)).getText().toString();
                String Answer3 = ((EditText) findViewById(R.id.EnterAnswer3)).getText().toString();
                if (Question.equals("") || Answer1.equals("") || Answer2.equals("") || Answer3.equals("")) {
                    Toast.makeText(AddCardActivity.this, "Please enter a question and three answers!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent data = new Intent();
                    data.putExtra("string1", Question);
                    data.putExtra("string2", Answer1);
                    data.putExtra("string3", Answer2);
                    data.putExtra("string4", Answer3);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}