package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.vacationapp.R;
public class VacationExcursionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_excursion_details);

        // Get the views
        TextView vacationName = findViewById(R.id.vacationName);
        TextView excursionName = findViewById(R.id.excursionName);
        TextView excursionStartDate = findViewById(R.id.excursionStartDate);
        TextView excursionEndDate = findViewById(R.id.excursionEndDate);

        // Ge the text from the previous page
        String vacationTitle = getIntent().getStringExtra("vacationTitle");
        String excursionTitle = getIntent().getStringExtra("excursionTitle");
        String excursionStartDateStr = getIntent().getStringExtra("excursionStartDate");
        String excursionEndDateStr = getIntent().getStringExtra("excursionEndDate");

        // Add the text in
        vacationName.setText(vacationTitle);
        excursionName.setText(excursionTitle);
        excursionStartDate.setText(excursionStartDateStr);
        excursionEndDate.setText(excursionEndDateStr);
    }
}