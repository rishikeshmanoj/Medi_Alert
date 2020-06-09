package com.hollow.thesolitaryowl.negotium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hollow.thesolitaryowl.negotium.adapters.TaskAdapter;
import com.hollow.thesolitaryowl.negotium.model.Task;
import com.hollow.thesolitaryowl.negotium.tools.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ViewTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Task");

        recyclerView = findViewById(R.id.recyclerView);

        taskAdapter = new TaskAdapter(taskList);
        linearLayoutManager = new LinearLayoutManager(ViewTaskActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskAdapter);
        fetchData();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Task task = taskList.get(position);
                        Intent recyclerView = new Intent(getApplicationContext(), TaskDetailActivity.class);
                        recyclerView.putExtra("title",task.getTitle() );
                        recyclerView.putExtra("date",task.getDate() );
                        recyclerView.putExtra("description",task.getDescription() );

                        startActivity(recyclerView);

                        }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );

    }

    private void fetchData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    taskList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Task task = snapshot.getValue(Task.class);
                        taskList.add(task);
                    }
                    taskAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
