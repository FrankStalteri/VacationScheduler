package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.List;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vacationapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.Locale;

import database.Repository;
import entities.Vacation;
import entities.Excursion;

public class VacationDetails extends AppCompatActivity {

    EditText vacationTitleText;
    EditText vacationLodgingText;
    EditText vacationStartText;
    EditText vacationEndText;
    int vacationId;
    String title;
    String lodging;
    String start_date;
    String end_date;
    Vacation currentVac;
    int numExc;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details_excursion_list);

        repository = new Repository(getApplication());
        vacationTitleText = findViewById(R.id.vacation_title);
        vacationLodgingText = findViewById(R.id.vacation_lodging);
        vacationStartText = findViewById(R.id.startDate);
        vacationEndText = findViewById(R.id.endDate);

        vacationId = getIntent().getIntExtra("vacationId", -1);
        title = getIntent().getStringExtra("vacationTitle");
        lodging = getIntent().getStringExtra("vacationLodging");
        start_date = getIntent().getStringExtra("vacationStartDate");
        end_date = getIntent().getStringExtra("vacationEndDate");

        if (title != null) {
            vacationId = getIntent().getIntExtra("vacationId", -1);
            vacationTitleText.setText(title);
            vacationLodgingText.setText(lodging);
            vacationStartText.setText(start_date);
            vacationEndText.setText(end_date);
        }

        vacationStartText = findViewById(R.id.startDate);
        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dateFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                updateLabelStart();
            }

        };

        vacationStartText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(VacationDetails.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        vacationEndText = findViewById(R.id.endDate);
        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dateFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                updateLabelEnd();
            }

        };

        vacationEndText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(VacationDetails.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.enter_excursion_details);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VacationDetails.this, ExcursionDetails.class);
                intent.putExtra("vacationId", vacationId);
                startActivity(intent);
            }
        });
        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelStart();
            }

        };

        RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
        repository = new Repository(getApplication());
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Excursion> excursionList = new ArrayList<>();
        for (Excursion e : repository.getAllExcursions()) {
            if (e.getVacationId() == vacationId) excursionList.add(e);
        }
        excursionAdapter.setExcursions(excursionList);

    }

    private void updateLabelStart() {
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        vacationStartText.setText(simpleDateFormat.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

        vacationEndText.setText(simpleDateFormat.format(myCalendarEnd.getTime()));
    }

    private boolean dateCheck() {

        Date vacationStartDate = new Date();
        try {
            vacationStartDate = new SimpleDateFormat("MM/dd/yy").parse(start_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date vacationEndDate = new Date();
        try {
            vacationEndDate = new SimpleDateFormat("MM/dd/yy").parse(end_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Check if the end date is before the start date
        if (vacationStartDate.before(vacationEndDate)){
            return true;
        }else{
            return false;
        }
    }

    public boolean dateValidationStart(String start_date){

        if(start_date.trim().equals("")){
            return true;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            sdf.setLenient(false);
            try{
                Date validDate = sdf.parse(start_date);

            }catch (ParseException e){
                e.printStackTrace();
                Toast.makeText(VacationDetails.this, "Please enter a valid date.", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;

        }

    }

    public boolean dateValidationEnd(String end_date){

        if(end_date.trim().equals("")){
            return true;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
            sdf.setLenient(false);
            try{
                Date validDate = sdf.parse(end_date);

            }catch (ParseException e){
                e.printStackTrace();
                Toast.makeText(VacationDetails.this, "Please enter a valid date.", Toast.LENGTH_LONG).show();
                return false;
            }

            return true;

        }

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.vacation_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();

        if (id == R.id.save) {

            start_date = vacationStartText.getText().toString();
            end_date = vacationEndText.getText().toString();

            if (dateCheck() && dateValidationStart(start_date) && dateValidationEnd(end_date)) {

                if (vacationId == -1) {

                    String title = vacationTitleText.getText().toString();
                    String lodging = vacationLodgingText.getText().toString();

                    Vacation newVacation = new Vacation(0, title, lodging, start_date, end_date);

                    repository.insert(newVacation);

                    Toast.makeText(VacationDetails.this, "Vacation added successfully", Toast.LENGTH_LONG).show();

                    this.finish();
                } else {

                    String title = vacationTitleText.getText().toString();
                    String lodging = vacationLodgingText.getText().toString();
                    String start = vacationStartText.getText().toString();
                    String end = vacationEndText.getText().toString();

                    Vacation newVacation = new Vacation(vacationId, title, lodging, start, end);

                    repository.update(newVacation);

                    Toast.makeText(VacationDetails.this, "Vacation added successfully", Toast.LENGTH_LONG).show();

                    this.finish();

                }

                return true;

            }else{
                Toast.makeText(VacationDetails.this, "Please make the end date after the start date", Toast.LENGTH_LONG).show();
                return false;
            }

        }

        if (id == R.id.delete) {
            for (Vacation vacation : repository.getVacations()) {
                if (vacation.getVacationId() == Integer.parseInt(String.valueOf(vacationId)))
                    currentVac = vacation;
            }

            numExc = 0;
            for (Excursion excursion : repository.getAllExcursions()) {
                if (excursion.getVacationId() == Integer.parseInt(String.valueOf(vacationId)))
                    ++numExc;
            }

            if (numExc == 0) {
                repository.delete(currentVac);
                Toast.makeText(VacationDetails.this, currentVac.getVacationTitle() + " was deleted", Toast.LENGTH_LONG).show();
                this.finish();
            } else {
                Toast.makeText(VacationDetails.this, "Can't delete a vacation with excursions", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        if (id == R.id.share) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_TEXT, vacationTitleText.getText().toString() + " " + vacationLodgingText.getText().toString() + " " +
                    vacationStartText.getText().toString() + " " + vacationEndText.getText().toString());
            intent.putExtra(Intent.EXTRA_TITLE, "This destination looks amazing!");
            intent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(intent, null);
            startActivity(shareIntent);
            return true;
        }

        if (id == R.id.start_alert)
        {
            String dateFromScreen = vacationStartText.getText().toString();
            String dateFormat = "MM/dd/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
            Date myDate = null;
            try {
                myDate = simpleDateFormat.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try{
                Long trigger = myDate.getTime();
                Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
                intent.putExtra("key", title + " is starting");

                PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++MainActivity.nAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
            catch (Exception e){

            }
            return true;
        }

        if (id == R.id.end_alert) {
            String dateFromScreen = vacationStartText.getText().toString();
            String dateFormat = "MM/dd/yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
            Date myDate = null;

            try {
                myDate = simpleDateFormat.parse(dateFromScreen);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try{
                Long trigger = myDate.getTime();
                Intent intent = new Intent(VacationDetails.this, MyReceiver.class);
                intent.putExtra("key", title + " is ending");

                PendingIntent sender = PendingIntent.getBroadcast(VacationDetails.this, ++MainActivity.nAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);}
            catch (Exception e){

            }
            return true;

        }

        if (id == R.id.refresh) {

            RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
            repository = new Repository(getApplication());

            final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
            recyclerView.setAdapter(excursionAdapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            List<Excursion> filteredExcursions = new ArrayList<>();

            for (Excursion e : repository.getAllExcursions()) {
                if (e.getVacationId() == vacationId) filteredExcursions.add(e);
            }
            excursionAdapter.setExcursions(filteredExcursions);

            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.excursion_recycler_view);
        final ExcursionAdapter excursionAdapter = new ExcursionAdapter(this);
        recyclerView.setAdapter(excursionAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Excursion> filteredExcursions = new ArrayList<>();

        for (Excursion e : repository.getAllExcursions()) {
            if(e.getVacationId() == vacationId) filteredExcursions.add(e);
        }
        excursionAdapter.setExcursions(filteredExcursions);
    }
}