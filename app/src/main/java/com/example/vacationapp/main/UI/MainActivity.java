package com.example.vacationapp.main.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.vacationapp.R;

public class MainActivity extends AppCompatActivity {

    // Used for the Pending Intent of the Excursion and Vacation Details pages
    public static int nAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the enter button
        Button button = findViewById(R.id.enter);

        // Direct to the Vacation List page
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VacationList.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Create the menu; add items to the bar if it is present.
        getMenuInflater().inflate(R.menu.base_menu, menu);
        return true;
    }
}