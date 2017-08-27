package com.absjbd.todoapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.absjbd.todoapp.Adapter.TodoAdapter;
import com.absjbd.todoapp.Controller.MyDBFunctions;
import com.absjbd.todoapp.Model.TodoModel;
import com.absjbd.todoapp.R;

import java.util.ArrayList;

/**
 * Created by absjabed on 08/19/17.
 */
public class TodoListActivity extends AppCompatActivity {

    private ListView lvItems;
    String[] data;
    ArrayList<TodoModel> todoModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        lvItems = (ListView) findViewById(R.id.lvItems);

       final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());
        todoModels = mf.getAllTodoData();

        final TodoAdapter todoAdapter = new TodoAdapter(this, todoModels);
        lvItems.setAdapter(todoAdapter);

        lvItems.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ItemEditActivity.class);
                i.putExtra("MyKEY", position);
//                Toast.makeText(TodoListActivity.this, position+" : and -3 :"+(position-3), Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });


        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {

                        final int posFinal = pos;

 //                       Toast.makeText(TodoListActivity.this, pos+" : and -3 :"+(pos-3), Toast.LENGTH_SHORT).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (!isFinishing()){
                                    new AlertDialog.Builder(TodoListActivity.this)
                                            .setMessage("Delete this Todo item?")
                                            .setCancelable(true)
                                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    mf.delete_todo(mf.fetch_todo(posFinal));
                                                    Toast.makeText(getApplicationContext(), "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                                    /*todoModels = mf.getAllTodoData();
                                                    TodoAdapter todoAdapter = new TodoAdapter(TodoListActivity.this, todoModels);
                                                    lvItems.setAdapter(todoAdapter);*/
                                                }
                                            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing
                                        }
                                    })
                                            .setIcon(android.R.drawable.ic_delete)
                                            .show();
                                }
                            }
                        });
                        return true;
                    }

                });


    }

}
