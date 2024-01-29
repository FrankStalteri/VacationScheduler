package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.vacationapp.R;
import java.util.ArrayList;
import database.Repository;
import entities.Excursion;
import entities.Vacation;

public class VacationList extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<Vacation> vacationList = new ArrayList<>();
    Repository repository;
    ArrayList<Excursion> excursionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        Button button = findViewById(R.id.button2);
        repository = new Repository(getApplication());

        // Get the vacations and excursions from the Room database
        vacationList.addAll(repository.getVacations());
        excursionList.addAll(repository.getAllExcursions());

        for (int i = 0; i < vacationList.size(); i++) {
            System.out.println(vacationList.get(i));
        }
        for (int j = 0; j < excursionList.size(); j++) {
            System.out.println(excursionList.get(j));
        }

        // Setup the adapter for the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        VacationAdapter adapter = new VacationAdapter(this, vacationList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });
    }

    // Adds click feature to each recycler view row item
    @Override
    public void onItemClick(int position) {
        // Move to the page to display the whole vacation and excursion details
        Intent intent = new Intent(VacationList.this, VacationExcursionDetails.class);

        // Send over the data needed to populate the fields in the VacationExcursionDetails file
        intent.putExtra("vacationTitle", vacationList.get(position).getVacationTitle());
        intent.putExtra("excursionTitle", excursionList.get(position).getExcursionTitle());
        intent.putExtra("excursionStartDate", excursionList.get(position).getExcursionStartDate());
        intent.putExtra("excursionEndDate", vacationList.get(position).getVacationEndDate());

        startActivity(intent);
    }
}