package com.example.gdziu.calmaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Widok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widok);
        Bundle extras = getIntent().getExtras();
        try {
            if(extras.getSerializable("element") !=
                    null) {
                Wydarzenie wydarzenie = (Wydarzenie)
                        extras.getSerializable("element");

                TextView nazwa = (TextView)
                        findViewById(R.id.edit_event_name);
                TextView lokalizacja = (TextView)
                        findViewById(R.id.edit_event_place);
                TextView data = (TextView)
                        findViewById(R.id.edit_event_date);

                nazwa.setText(wydarzenie.getSummary());
                lokalizacja.setText(wydarzenie.getLocation());
                data.setText(wydarzenie.getStartDate());
            }
        }catch(Exception ex) {
        }
    }
    public void wstecz (View view)
    {
        Intent intencja = new Intent();
        setResult(RESULT_OK, intencja);
        finish();
    }
}
