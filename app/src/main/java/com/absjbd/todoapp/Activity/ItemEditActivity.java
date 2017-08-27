package com.absjbd.todoapp.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
public class ItemEditActivity extends AppCompatActivity {

    EditText etUpdate, etToDoDate;
    Spinner spnrS, spnrP;;
    Button updateBtn;
    String[] priorities = {"High", "Medium","Low"};
    String[] status = {"To-Do", "Done"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        etUpdate = (EditText) findViewById(R.id.edittextE);
        etToDoDate = (EditText) findViewById(R.id.etTodoDateE);
        spnrP = (Spinner) findViewById(R.id.spinrPriorityE);
        spnrS  = (Spinner) findViewById(R.id.spinrStatusE);
        updateBtn = (Button) findViewById(R.id.updatedatabase);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,priorities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrP.setAdapter(aa);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,status);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrS.setAdapter(aa2);



        final int rec_pos = getIntent().getIntExtra("MyKEY", 999);

        final MyDBFunctions obj = new MyDBFunctions(getApplicationContext());

        TodoModel todo = obj.fetch_todo(rec_pos+1);
        etUpdate.setText(todo.getTodoName());
        etToDoDate.setText(todo.getDueDate());

        if(todo.getPriority().equals("High")){
            spnrP.setSelection(0);
        }else if(todo.getPriority().equals("Medium")){
            spnrP.setSelection(1);
        }else{
            spnrP.setSelection(2);
        }


        if(todo.getStatus().equals("To-Do")){
            spnrS.setSelection(0);
        }else if(todo.getPriority().equals("Done")){
            spnrS.setSelection(1);
        }else{
            spnrS.setSelection(1);
        }
        etUpdate.setSelection(etUpdate.getText().length());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String todoString = etUpdate.getText().toString();
                String date = etToDoDate.getText().toString();
                String status = spnrS.getSelectedItem().toString();
                String priority = spnrP.getSelectedItem().toString();

                obj.update_todo((rec_pos+1), todoString, date, status, priority);
                Toast.makeText(ItemEditActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
