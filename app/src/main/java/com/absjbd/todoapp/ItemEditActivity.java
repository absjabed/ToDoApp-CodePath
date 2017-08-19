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
public class ItemEditActivity extends AppCompatActivity {

    EditText etUpdate;
    Button updateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        etUpdate = (EditText) findViewById(R.id.edittext);
        updateBtn = (Button) findViewById(R.id.updatedatabase);

        final int rec_pos = getIntent().getIntExtra("MyKEY", 999);

        final MyDBFunctions obj = new MyDBFunctions(getApplicationContext());

        etUpdate.setText(obj.fetch_todo(rec_pos+1));
        etUpdate.setSelection(etUpdate.getText().length());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.update_todo((rec_pos+1), etUpdate.getText().toString());
                Toast.makeText(ItemEditActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
