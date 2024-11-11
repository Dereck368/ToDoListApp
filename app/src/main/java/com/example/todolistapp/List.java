package com.example.todolistapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class List extends AppCompatActivity {

    private ToDoList list;
    private SharedPreferences sp;

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

        //gets name from main activity
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        ((TextView)findViewById(R.id.Header)).setText(name);

        // get list from shared Prefrence
        sp = this.getSharedPreferences(name, Context.MODE_PRIVATE);
        list = getSavedList(name);

        // remakes last saved list
        remakeList();

        /*  todo  go over what ever is at the bottom, finished most of it tho
            ok what we will do is make a Todolist from the todolist object
            have a add button function to add todolist elements
            this will call upon function
            function will take node address and display it
            extra things to add ...
            we will then take this object and save it in a shareprefrence somehow
            (still have to figure that out) GSON

         */

    }

    //todo add delete function
    //todo add rename task function

    // makes a new task
    public void addTask(View v) {
        // get text and make ItemName empty
        EditText text = findViewById(R.id.ItemName);
        String taskName = text.getText().toString();
        text.setText("");

        //adds node to list if list isn't full
        Node<String> node = list.addToList(taskName);

        if (!node.isEmpty()) {
            saveToDoList(list);
            makeNodeButton(taskName, node);
        } else {
            Toast.makeText(this,
                    "list is full: delete item before adding new one",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //makes node button and add it to the view
    // reasoning I want to have access to the address because most of the methods
    // in ToDoList depends on having the address of the Node.
    private void makeNodeButton(String taskName, Node<String> node) {
        NodeButton<String> task = new NodeButton<>(this, node);
        task.setText(taskName);
        LinearLayout tasks = ((LinearLayout) findViewById(R.id.ItemsList));
        tasks.addView(task);
    }

    private void saveToDoList(ToDoList list) {
        SharedPreferences.Editor editor = sp.edit();

        // convert list to Json
        Gson gson = new Gson();
        String json = gson.toJson(list);

        // put json into sharePrefrence
        editor.putString(list.getName(), json);
        editor.apply();
    }

    private ToDoList getSavedList(String name) {
        String json = sp.getString(name, null);

        if (json == null) { // if sp doesn't have name
            ToDoList newList = new ToDoList(name); // create new list
            saveToDoList(newList); // save the list to sp
            return newList;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ToDoList>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void remakeList() {
        Node<String> curr = list.getHead();
        while (curr != null) {
            makeNodeButton(curr.getData(), curr);
            curr = curr.getNext();
        }
    }
}