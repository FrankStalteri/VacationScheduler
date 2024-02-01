package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.vacationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import database.Repository;

import entities.Vacation;

public class VacationList extends AppCompatActivity {
    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_list);

        RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);

        recyclerView.setAdapter(vacationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new Repository(getApplication());
        List<Vacation> allVacations = repository.getVacations();

        FloatingActionButton fab = findViewById(R.id.vacationListfab);

        vacationAdapter.setVacations(allVacations);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vacation_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.vacation_refresh) {
            RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
            final VacationAdapter adapter = new VacationAdapter(this);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setVacations(repository.getVacations());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.vacation_recycler_view);
        final VacationAdapter vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter.setVacations(repository.getVacations());
    }
}