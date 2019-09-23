package com.example.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText NAMA;
    TextView textView;
    db_helper controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NAMA = (EditText)findViewById(R.id.input_nama);
        textView = (TextView)findViewById(R.id.textView);

        controller = new db_helper(this,"",null,1);
    }
    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.insert_btn:
                try{
                }catch(SQLiteException e){
                    Toast.makeText(this, "SUDAH ADA", Toast.LENGTH_SHORT).show();
                }
                controller.insert_data(NAMA.getText().toString());
                break;
            case R.id.edit_btn:
                AlertDialog.Builder dialog =  new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("INPUTKAN NAMA ANDA");

                final EditText new_nama = new EditText(this);
                dialog.setView(new_nama);

                dialog.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.edit_data(NAMA.getText().toString(),new_nama.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.delete_btn:
                controller.delete_data(NAMA.getText().toString());
                break;
            case R.id.list_btn:
                controller.list_biodata(textView);
                break;
        }
    }
}
