package com.absjbd.todoapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.absjbd.todoapp.Controller.MyDBFunctions;
import com.absjbd.todoapp.Model.TodoModel;
import com.absjbd.todoapp.R;

import java.util.Calendar;

/**
 * Created by absjabed on 08/19/17.
 */
public class ItemAddActivity extends AppCompatActivity {

    EditText etTodoNewItem, etToDoDate;
    Spinner spnrS, spnrP;
    Button addItemBtn;
    String[] priorities = {"High", "Medium","Low"};
    String[] status = {"To-Do", "Done"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_add);

        final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());

        addItemBtn = (Button) findViewById(R.id.btnAddItem);
        etTodoNewItem = (EditText) findViewById(R.id.etNewItem);
        etToDoDate = (EditText) findViewById(R.id.etTodoDate);
        spnrP = (Spinner) findViewById(R.id.spinrPriority);
        spnrS  = (Spinner) findViewById(R.id.spinrStatus);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,priorities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrP.setAdapter(aa);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrS.setAdapter(aa2);


        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoString = etTodoNewItem.getText().toString();
                String date = etToDoDate.getText().toString();
                String status = spnrS.getSelectedItem().toString();
                String priorities = spnrP.getSelectedItem().toString();

                TodoModel dt = new TodoModel(todoString,date,status,priorities);

                mf.addingDataToTable(dt);
                etTodoNewItem.setText(null);
                etToDoDate.setText(null);
            }
        });



    }
}
