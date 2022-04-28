package vn.edu.fithou.quanlychitieu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.fithou.quanlychitieu.R;

public class SearchActivity extends AppCompatActivity {
    Button btnsearch,btndelete;
    EditText edtsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnsearch=(Button) findViewById(R.id.btnsearch);
        btndelete=(Button) findViewById(R.id.btndelete);
        edtsearch=(EditText) findViewById(R.id.edtsearch);

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e=edtsearch.getText().toString();
                SQLiteDatabase simpledb= getApplicationContext().openOrCreateDatabase("SQLiteUtil", Context.MODE_PRIVATE,null);
                simpledb.execSQL("delete from t1 where name='"+e+"'",null);

                Toast.makeText(SearchActivity.this, "Data Delete Successfull", Toast.LENGTH_SHORT).show();
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e=edtsearch.getText().toString();
                SQLiteDatabase simpledb= getApplicationContext().openOrCreateDatabase("SQLiteUtil", Context.MODE_PRIVATE,null);
                Cursor c = simpledb.rawQuery("select* from t1 where name='"+e+"'",null);
                if(c.getCount()==0){
                    Toast.makeText(getApplicationContext(), "no record found", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()){
                    buffer.append("Name \t"+c.getString(0)+"\n");
                    buffer.append("Mobile \t"+c.getString(1)+"\n");
                    buffer.append("Email \t"+c.getString(1)+"\n");
                }
                Toast.makeText(getApplicationContext(), "Result \n"+buffer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}