package com.example.todolistapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private Set<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // This set action for addItem
        FloatingActionButton fab = findViewById(R.id.addItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createToDoList(view);
            }
        });

        // Initialize SharedPrefrences and names
        sp = this.getSharedPreferences("names_pref", MODE_PRIVATE);
        names = sp.getStringSet("Used_names", new HashSet<>());

        // Remake already saved List
        for (String name: names) {
            createList(name);
        }
    }

    //todo add delete function
    public void createToDoList(View v) {
        // this will take the text and make the EditText widget blank
        EditText text = findViewById(R.id.ListName);
        String name = text.getText().toString();
        text.setText("");

        // If user didn't enter any name and if name isn't unique -> do NOTHING
        if (name.isEmpty()) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        } else if (names.contains(name)) {
            Toast.makeText(this, "You may NOT use the same name", Toast.LENGTH_LONG).show();
            return;
        }

        // add name to set and save set to sharePreference
        names.add(name);
        saveUsedListNames(names);

        // create list on navigate
        createList(name);
    }

    private void createList(String name) {
        // making button for list
        Button toDoList = new Button(this);
        toDoList.setText(name);

        // add button to navigation
        LinearLayout nav = findViewById(R.id.navigate);
        nav.addView(toDoList);

        // assign button functionality
        toDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send name of button to Activity_list and opens Activity_list
                Intent intent = new Intent(MainActivity.this, List.class);
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
        });
    }

    private void saveUsedListNames(Set<String> names) {
        SharedPreferences.Editor editor = sp.edit();

        editor.putStringSet("Used_names", names);
        editor.apply();
    }
}