package com.absjbd.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
/**
 * Created by absjabed on 08/19/17.
 */
public class TodoListActivity extends AppCompatActivity {

    private ListView lvItems;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);


        lvItems = (ListView) findViewById(R.id.lvItems);
       final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());

        data = mf.my_data();

        lvItems.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.lview, R.id.mytext, data));

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ItemEditActivity.class);
                i.putExtra("MyKEY", position);
                startActivity(i);
            }
        });


        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {

                        final int posFinal = pos;
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
                                                    mf.delete_todo(mf.fetch_todo(posFinal+1));
                                                    Toast.makeText(getApplicationContext(), "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                                    data = mf.my_data();
                                                    lvItems.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.lview, R.id.mytext, data));
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
