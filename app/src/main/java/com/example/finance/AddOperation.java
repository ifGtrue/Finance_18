package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Objects;

import database.data.Operation;

public class AddOperation extends AppCompatActivity {

    ImageView to_main_menu;
    Dialog calendar;

    ImageButton callCalendar;



    EditText Op_sum;
    EditText Op_name;
    EditText Op_com;

    Button confirmBtt;


    private static boolean  RoG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);

        to_main_menu = findViewById(R.id.to_main_menu);
        to_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddOperation.this, MainActivity.class);
                startActivity(intent);
            }
        });



        callCalendar = findViewById(R.id.calendar);
        calendar = new Dialog(AddOperation.this);
        callCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCalendar();
            }
        });





        Op_sum = findViewById(R.id.editMoney);
        Op_name = findViewById(R.id.editDscr);
        Op_com = findViewById(R.id.editCom);

        confirmBtt = findViewById(R.id.confirm_button);





        confirmBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sum = Op_sum.getText().toString();
                String name = Op_name.getText().toString();
                String com = Op_com.getText().toString();

                Operation op1 = new Operation(name, sum, "OOO", "OOO", com);

                AppDataBase.getInstance(AddOperation.this).getOperationDao().addOperation(op1);

                finish();
            }
        });

    }


    private void ShowCalendar() {
        calendar.setContentView(R.layout.calendar);
        Objects.requireNonNull(calendar.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        calendar.show();


    }




}