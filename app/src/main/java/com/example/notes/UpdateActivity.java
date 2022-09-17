package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Controller.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    private EditText update_note;
    private Button update_button;
    private String id, note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_note = findViewById(R.id.update_note);
        update_button = findViewById(R.id.update_button);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.updateData(id, note);
                Log.d("update_button.setOnClickListener", "did");
            }
        });

    }
    
    public void getAndSetIntentData()
    {
        if(getIntent().hasExtra("note" ) && getIntent().hasExtra("id"))
        {
            id = getIntent().getStringExtra("id");
            note = getIntent().getStringExtra("note");
            update_note.setText(note);
        }
        else{
            Toast.makeText(this, "Not data.", Toast.LENGTH_SHORT).show();
        }
    }
}