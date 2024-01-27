package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vacationapp.R;

import database.Repository;
import entities.Excursion;

public class ExcursionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);

        Button button = findViewById(R.id.excursionSubmitBtn);
        EditText excursionTitle = findViewById(R.id.excursionInput);
        EditText excursionStartDate = findViewById(R.id.excursionDateInput);

        Repository repository = new Repository(getApplication());

        Excursion excursion = new Excursion();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = excursionTitle.getText().toString();
                String startDate = excursionStartDate.getText().toString();

                excursion.setExcursionTitle(title);
                excursion.setExcursionStartDate(startDate);

                repository.insert(excursion);

                Intent intent = new Intent(ExcursionDetails.this, VacationList.class);
                startActivity(intent);
            }
        });
    }
}