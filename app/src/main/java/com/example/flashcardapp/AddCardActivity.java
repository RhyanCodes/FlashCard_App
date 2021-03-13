package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

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
                Intent data = new Intent();
                data.putExtra("string1", Question);
                data.putExtra("string2", Answer1);
                data.putExtra("string3", Answer2);
                data.putExtra("string4", Answer3);
                setResult(RESULT_OK, data);
                finish();
            }
        });


    }
}