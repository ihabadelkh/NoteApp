package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Controller.Adapter;
import Controller.DatabaseHelper;
import Model.Note;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private DatabaseHelper databaseHelper;
    private ArrayList<String> id, note;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView         = findViewById(R.id.recyclerView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(this);
        id = new ArrayList<>();
        note = new ArrayList<>();

        displayData();

        adapter = new Adapter(this, id, note);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    public void displayData()
    {
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                note.add(cursor.getString(1));

            }
        }
    }
}