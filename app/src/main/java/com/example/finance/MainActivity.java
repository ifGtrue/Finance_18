package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import database.data.Operation;

public class MainActivity extends AppCompatActivity {


    List<Operation> OperList;

    private final static List<Item> items = new ArrayList<>();

    ImageButton ForCheck;
    private static boolean  RoG;
    MaterialButton show;
    MaterialButton show1;
    Dialog regular_or_single;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter = new MyAdapter(items);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (OperList != null) {
            for (Operation op : OperList) {
                items.add(new Item(op.getOp_name(), op.getOp_sum(), op.getOp_comm(), true));
            }
        }


        show = findViewById(R.id.plus_button);
        show1 = findViewById(R.id.minus_button);
        regular_or_single = new Dialog(MainActivity.this);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoG = true;
                showCustomDialog();
            }
        });
        show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RoG = false;
                showCustomDialog();
            }
        });







        ForCheck = findViewById(R.id.menu);



        ForCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringBuilder sb = new StringBuilder();
                for(Operation op : OperList){
                    sb.append(op.getOp_name() + " : " + op.getOp_sum());
                    sb.append("\n");
                }
                String finalData = sb.toString();
                Toast.makeText(MainActivity.this, ""+finalData, Toast.LENGTH_SHORT).show();
            }
        });



    }


    private void showCustomDialog() {
        regular_or_single.setContentView(R.layout.regular_or_single);
        Objects.requireNonNull(regular_or_single.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        regular_or_single.show();
        MaterialButton btt_on_AddOperation = regular_or_single.findViewById(R.id.single_btt);

        View.OnClickListener oclBtt_on_AddOperation = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddOperation.class);
                startActivity(intent);
            }
        };
        btt_on_AddOperation.setOnClickListener(oclBtt_on_AddOperation);
    }


}