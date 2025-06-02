package com.example.sqllitedatabase;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn;
    EditText et1, et2;
    Spinner sp;
    Toolbar tb;
    String name, qualification, dateofbirth;

    private String[] courses = {"B.tech", "M.tech", "Phd", "B.sc", "B.C.A", "M.C.A", "B.B.A"};

   com.example.sqllitedatabase.DatabaseHelper dbHelper;  // Database helper instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_record);
        tb= findViewById(R.id.tb);
        btn = findViewById(R.id.btn);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        sp = findViewById(R.id.sp);

        setSupportActionBar(tb);

        // Optional: enable back arrow on toolbar if you want to navigate back
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);  // Use your back arrow icon
        }

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddRecord.this,MainActivity.class);
                startActivity(intent);
            }
        });

        // Initialize database helper
        dbHelper = new com.example.sqllitedatabase.DatabaseHelper(this);


        // Spinner setup
        ArrayAdapter<String> ad = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                courses
        );
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(this);

        // DatePicker setup for dob EditText
        et2.setFocusable(false);
        et2.setOnClickListener(v -> showDatePicker());

        // Button click saves data to DB
        btn.setOnClickListener(v -> {
            name = et1.getText().toString().trim();
            dateofbirth = et2.getText().toString().trim();
            qualification = sp.getSelectedItem().toString();

            if (name.isEmpty() || dateofbirth.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = dbHelper.insertStudent(name, dateofbirth, qualification);
            if (inserted) {
                Toast.makeText(getApplicationContext(), "Record saved successfully!", Toast.LENGTH_LONG).show();
                et1.setText("");
                et2.setText("");
                sp.setSelection(0);
            } else {
                Toast.makeText(getApplicationContext(), "Failed to save record.", Toast.LENGTH_LONG).show();
            }
        });

        // Handle window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(AddRecord.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    et2.setText(selectedDate);
                }, year, month, day);

        dpd.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // You can keep the toast or remove it
        Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No action needed
    }
}
