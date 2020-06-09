package com.hollow.thesolitaryowl.negotium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    public TextView title1;
    public TextView date1;
    public TextView description1;

    private String title;
    private String date;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        title1=findViewById(R.id.title1);
        date1=findViewById(R.id.date1);
        description1=findViewById(R.id.description1);

        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        description = getIntent().getStringExtra("description");

        title1.setText(title);
        date1.setText(date);
        description1.setText(description);

    }
}
