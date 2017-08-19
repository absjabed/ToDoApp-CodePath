package com.absjbd.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * Created by absjabed on 08/19/17.
 */
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onClickViewTodo(View view) {
        Intent intent = new Intent(getApplicationContext(),TodoListActivity.class);
        startActivity(intent);
    }


    public void onAddTodoClick(View view) {
        Intent intent = new Intent(getApplicationContext(),ItemAddActivity.class);
        startActivity(intent);
    }
}
