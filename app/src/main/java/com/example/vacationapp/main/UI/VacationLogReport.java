package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.example.vacationapp.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import database.Repository;
import entities.Vacation;

public class VacationLogReport extends AppCompatActivity {

    private List<Vacation> vacationList;

    private String name;

    private String startDate;

    private String endDate;

    private String information;

    private String logInfo;

    // Logging vacation information
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

            information = name + "                   " + startDate + "                   " + endDate + "\n";
            logInfo += information;

            info.append(information);
        }
        // Date reported generated
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Log.d("Vacation Report", "Vacation Information: \n" + repository.getVacations());

        writeLogToFile(logInfo + "\n" + "Report Generated: " + dateTimeFormatter.format(now));
    }
    // Write log to external file
    public void writeLogToFile(String log) {
        try {
            File logFile = new File("/storage/emulated/0/Documents", "vacation_log.txt");

            logFile.getParentFile().mkdirs();

            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            FileWriter writer = new FileWriter(logFile, true);
            writer.append(log);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}