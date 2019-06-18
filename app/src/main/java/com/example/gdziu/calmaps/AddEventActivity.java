package com.example.gdziu.calmaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }

    public void sendMessage(View view) {
        EditText name = (EditText) findViewById(R.id.edit_event_name);
        EditText place = (EditText) findViewById(R.id.edit_event_place);
        EditText date = (EditText) findViewById(R.id.edit_event_date);
        String summary = name.getText().toString();
        String location = place.getText().toString();
        String startDate = date.getText().toString();
        //Intent intencja = new Intent();
        //intencja.putExtra("wpis", pole);
        Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
        intent.putExtra("summary", summary);
        intent.putExtra("location", location);
        intent.putExtra("startDate", startDate);
        startActivity(intent);
        //setResult(RESULT_OK, intencja); finish();
        //Toast.makeText(this, pole, Toast.LENGTH_SHORT).show();
    }
}
