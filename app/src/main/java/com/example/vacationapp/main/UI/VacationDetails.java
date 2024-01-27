package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vacationapp.R;

import database.Repository;
import entities.Vacation;

public class VacationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);

        Button button = findViewById(R.id.submitBtn);

        EditText titleTxt = findViewById(R.id.titleTxt);
        EditText lodgingTxt = findViewById(R.id.lodgingTxt);
        EditText startDateInput = findViewById(R.id.startDateInput);
        EditText endDateInput = findViewById(R.id.endDateInput);

        Repository repository = new Repository(getApplication());
        Vacation vacation = new Vacation();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringTitle = titleTxt.getText().toString();
                String stringLodging = lodgingTxt.getText().toString();
                String startDate = startDateInput.getText().toString();
                String endDate = endDateInput.getText().toString();

                vacation.setVacationTitle(stringTitle);
                vacation.setVacationLodging(stringLodging);
                vacation.setVacationStartDate(startDate);
                vacation.setVacationEndDate(endDate);

                repository.insert(vacation);

                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                startActivity(intent);
            }
        });
    }
}