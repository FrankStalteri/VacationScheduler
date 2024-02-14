package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.vacationapp.R;

import java.util.List;

import database.Repository;
import entities.Vacation;

public class VacationLogReport extends AppCompatActivity {

    private List<Vacation> vacationList;

    private String name;

    private String startDate;

    private String endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_log_report);

        Repository repository = new Repository(getApplication());

        TextView textView = findViewById(R.id.reportTxt);
        TextView info = findViewById(R.id.information);

        vacationList = repository.getVacations();

        String message = "Vacation Title:   " + "Vacation Start Date:   " + "Vacation End Date:";
        textView.append(message);

        for (int i = 0; i < vacationList.size(); i++) {
            name = vacationList.get(i).getVacationTitle();
            startDate = vacationList.get(i).getVacationStartDate();
            endDate = vacationList.get(i).getVacationEndDate();

            String information = name + "                   " + startDate + "                   " + endDate + "\n";

            info.append(information);
        }

        Log.d("Vacation Report", "Vacation Information: \n" + repository.getVacations());
    }
}