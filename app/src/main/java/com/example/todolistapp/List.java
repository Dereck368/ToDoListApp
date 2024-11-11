package com.example.todolistapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /* what I want to do
            second activity will take care of the linked list implementation in
            shared data

            so button will send us to second activity,
            second activity will take the intent from that button and load data
         */

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");

        /*
            ok what we will do is make a Todolist from the todolist object
            have a add button function to add todolist elements
            this will call upon function
            function will take node address and display it
            extra things to add ...
            we will then take this object and save it in a shareprefrence somehow
            (still have to figure that out)

         */
    }
}