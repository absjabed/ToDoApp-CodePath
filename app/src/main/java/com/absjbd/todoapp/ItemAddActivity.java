package com.absjbd.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by absjabed on 08/19/17.
 */
public class ItemAddActivity extends AppCompatActivity {

    EditText etTodoNewItem;
    Button addItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

        addItemBtn = (Button) findViewById(R.id.btnAddItem);
        etTodoNewItem = (EditText) findViewById(R.id.etNewItem);

        final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoString = etTodoNewItem.getText().toString();

                DataTemp dt = new DataTemp(todoString);
                mf.addingDataToTable(dt);
                etTodoNewItem.setText(null);
                Toast.makeText(getApplicationContext(), "New Todo added successfully!", Toast.LENGTH_LONG).show();
            }
        });



    }



}
