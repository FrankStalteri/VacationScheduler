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
import entities.Vacation;

public class VacationList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        Button button = findViewById(R.id.button2);
        Repository repository = new Repository(getApplication());
        ArrayList<Vacation> vacationList = new ArrayList<>();

        // Get the vacations from the Room database
        vacationList.addAll(repository.getVacations());

        // Setup the adapter for the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        VacationAdapter adapter = new VacationAdapter(this, vacationList);
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

}