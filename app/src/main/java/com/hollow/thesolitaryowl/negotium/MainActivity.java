package com.hollow.thesolitaryowl.negotium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addtask;
    private Button viewtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addtask = findViewById(R.id.addtask);
        viewtask = findViewById(R.id.viewtask);

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addtask = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(addtask);
            }
        });

        viewtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewtask = new Intent(MainActivity.this, ViewTaskActivity.class);
                startActivity(viewtask);
            }
        });


    }

}
