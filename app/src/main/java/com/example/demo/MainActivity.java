package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Spinner courseS;
    Spinner yearS;
    Button sub;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("Event");

        courseS=findViewById(R.id.course_spinner);
        yearS=findViewById(R.id.year_spinner);
        sub=findViewById(R.id.sub_btn);

        ArrayAdapter<String> courseAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.course));

        ArrayAdapter<String> yearAdapter=new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.year));

        courseS.setAdapter(courseAdapter);
        yearS.setAdapter(yearAdapter);

        courseS.getSelectedItem();
        sub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addEvent();
                    }
                }
        );
    }

    private void addEvent(){
        String course=courseS.getSelectedItem().toString();
        String year=yearS.getSelectedItem().toString();

        String id = databaseReference.push().getKey();

        Events events = new Events(course,year);

        databaseReference.child(id).setValue(events);

        Toast.makeText(this, "Event Added", Toast.LENGTH_SHORT).show();

    }
}
