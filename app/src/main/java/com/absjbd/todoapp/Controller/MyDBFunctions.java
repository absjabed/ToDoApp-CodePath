package com.absjbd.todoapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.absjbd.todoapp.Model.TodoModel;

import java.util.ArrayList;

/**
 * Created by absjabed on 08/19/17.
 */
public class MyDBFunctions extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "mydb";
    private static final String TABLE_NAME = "mytab";

    private static final String TAB_ID = "id";
    private static final String TAB_NAME = "name";
    private static final String TAB_DATE = "date";
    private static final String TAB_STATUS = "status";
    private static final String TAB_PRIORITY = "priority";
    private static final String MY_DEBUG_TAG = "jbdx";
    private Context ctx;
   private String sx;

    public MyDBFunctions(Context c){
       super(c, DATABSE_NAME, null, 1);
        ctx = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String s =
                "CREATE TABLE "+TABLE_NAME+" ("+
                        TAB_ID+" INTEGER PRIMARY KEY,"+
                        TAB_NAME+" TEXT,"+
                        TAB_DATE+" TEXT,"+
                        TAB_STATUS+" TEXT, "+
                        TAB_PRIORITY+" TEXT)";
        //sx = "Query Table Create: "+s;
        try{
            db.execSQL(s);
        }catch (SQLException sqle){
            sx = sqle.getMessage();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    // ---- ---- adding data to database --- -----

    public void addingDataToTable(TodoModel dt){

        SQLiteDatabase sqd  = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TAB_NAME, dt.getTodoName());
        cv.put(TAB_DATE, dt.getDueDate());
        cv.put(TAB_STATUS, dt.getStatus());
        cv.put(TAB_PRIORITY, dt.getPriority());

        long row = sqd.insert(TABLE_NAME, null, cv);
        sqd.close();

        Toast.makeText(ctx, sx, Toast.LENGTH_SHORT).show();
        if(row == -1){
            Toast.makeText(ctx, "Error Happend.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ctx, "Insert Done!", Toast.LENGTH_SHORT).show();
        }
    }


    // --- ---- showing data ------ ----

   public ArrayList<TodoModel> getAllTodoData() {

        SQLiteDatabase sq = this.getReadableDatabase();

        String q = "SELECT * FROM "+TABLE_NAME;

       ArrayList<TodoModel> resultList = new ArrayList<>();

       Cursor c = sq.rawQuery(q, null);

       while (c.moveToNext())
       {
           String todoName = c.getString(c.getColumnIndex(TAB_NAME+""));
           String dueDate = c.getString(c.getColumnIndex(TAB_DATE+""));
           String status = c.getString(c.getColumnIndex(TAB_STATUS+""));
           String priority = c.getString(c.getColumnIndex(TAB_PRIORITY+""));

           try
           {
               TodoModel todo = new TodoModel();
               todo.setTodoName(todoName);
               todo.setDueDate(dueDate);
               todo.setStatus(status);
               todo.setPriority(priority);

               resultList.add(todo);
           }
           catch (Exception e) {
               Log.e(MY_DEBUG_TAG, "Error " + e.toString());
           }

       }
        c.close();
       return resultList;
        /*c.moveToFirst();

        if(c.moveToFirst()){
            int counter = 0 ;
            do {
                recvied_data[counter] = c.getString(c.getColumnIndex(TAB_NAME+""));

                counter = counter+1;

            } while(c.moveToNext());

        }

        return recvied_data;*/
    }


   public TodoModel fetch_todo(int id) {

        SQLiteDatabase sq = this.getReadableDatabase();

        String q = "SELECT "+TAB_NAME+", "+TAB_DATE+","+TAB_STATUS+","+TAB_PRIORITY+" FROM "+TABLE_NAME+" WHERE "+TAB_ID+" = "+id;

        Cursor c = sq.rawQuery(q, null);
       TodoModel todo = new TodoModel();

        c.moveToFirst();

        if(c.moveToFirst()) {
            String todoName = c.getString(c.getColumnIndex(TAB_NAME+""));
            String dueDate = c.getString(c.getColumnIndex(TAB_DATE+""));
            String status = c.getString(c.getColumnIndex(TAB_STATUS+""));
            String priority = c.getString(c.getColumnIndex(TAB_PRIORITY+""));

            try
            {
                todo.setTodoName(todoName);
                todo.setDueDate(dueDate);
                todo.setStatus(status);
                todo.setPriority(priority);
            }
            catch (Exception e) {
                Log.e(MY_DEBUG_TAG, "Error " + e.toString());
            }
        }
       c.close();

        return todo;
    }


   public int update_todo(int id, String todoName, String date, String status, String priority) {

        SQLiteDatabase sq = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TAB_NAME, todoName);
        cv.put(TAB_DATE, date);
        cv.put(TAB_STATUS, status);
        cv.put(TAB_PRIORITY, priority);

        return sq.update(TABLE_NAME, cv, TAB_ID+" = ? ", new String[]{id+""});

    }


  public int delete_todo(TodoModel todo){

        SQLiteDatabase s = this.getWritableDatabase();

        return s.delete(TABLE_NAME, TAB_NAME+" = ?", new String[] {todo.getTodoName()});

    }


}
