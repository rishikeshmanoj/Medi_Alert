package com.hollow.thesolitaryowl.negotium;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hollow.thesolitaryowl.negotium.adapters.TaskAdapter;
import com.hollow.thesolitaryowl.negotium.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etitle;
    private EditText edescription;
    private EditText edate;
    private Button enter;
    private String title;
    private String description;
    private String date;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Task");


        etitle = findViewById(R.id.title);
        edescription = findViewById(R.id.description);
        edate = findViewById(R.id.date);

        enter = findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etitle.getText().toString().trim();
                description = edescription.getText().toString().trim();
                date = edate.getText().toString().trim();

                HashMap<String, String> dataPush = new HashMap<String, String>();
                dataPush.put("title", title);
                dataPush.put("description", description);
                dataPush.put("date", date);

                myRef.push().setValue(dataPush).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddTaskActivity.this, "Task Added!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddTaskActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                etitle.getText().clear();
                edescription.getText().clear();
                edate.getText().clear();

            }
        });

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);


        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.time_array,
                        android.R.layout.simple_spinner_item);


        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        staticSpinner.setAdapter(staticAdapter);
    }
  }