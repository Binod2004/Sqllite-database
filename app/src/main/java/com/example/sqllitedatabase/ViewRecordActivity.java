package com.example.sqllitedatabase;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllitedatabase.R;
import com.example.sqllitedatabase.RecordAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewRecordActivity extends AppCompatActivity {

    private RecyclerView rvRecords;
    private RecordAdapter recordAdapter;
    private ArrayList<Record>  recordList;

    private DatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record); // Make sure this matches your XML filename

        rvRecords = findViewById(R.id.rvRecords);
        recordList = new ArrayList<>();
        dbHandler = new DatabaseHelper(ViewRecordActivity.this);

        // getting our course array
        // list from db handler class.
        recordList = dbHandler.fetchRecords();

        // on below line passing our array list to our adapter class.
        recordAdapter = new RecordAdapter(recordList, ViewRecordActivity.this);


        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewRecordActivity.this, RecyclerView.VERTICAL, false);
        rvRecords.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        rvRecords.setAdapter(recordAdapter);

    }
}
