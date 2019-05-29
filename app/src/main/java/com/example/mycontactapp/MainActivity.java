package com.example.mycontactapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.mycontactapp.DatabaseHelper.COLUMN_NAME_CONTACT;
import static com.example.mycontactapp.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editName = findViewById(R.id.editText_name);
        myDb = new DatabaseHelper( this );
        Log.d( "MyContactApp", "DatabaseHelper: constructed the DatabaseHelper");


    }

    public void addData(View view){

        //add Log.d
        boolean isInserted = myDb.insertData(editName.getText().toString());

        if (isInserted == true){
          Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG ).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Failed - contact not inserted", Toast.LENGTH_LONG ).show();
        }
    }


    public void viewData(View view){
        Cursor res = myDb.getAllData();
        //put log.d's in here
        if (res.getCount() == 0){
            showMessage("Error", "No data found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //Append res columsn to buffer - see StringBuffer and Cursor API's
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Name: " + res.getString(1) + "\n");

        }
        showMessage("Data", buffer.toString());

    }

    public void showMessage(String title, String message){
        //put Log.d's in here
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.show();


    }
}
