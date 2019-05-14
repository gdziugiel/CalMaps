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
        EditText kontrolka = (EditText)findViewById(R.id.edit_event_name);
        String pole = kontrolka.getText().toString();
        //Intent intencja = new Intent();
        //intencja.putExtra("wpis", pole);
        Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
        intent.putExtra("event", pole);
        startActivity(intent);
        //setResult(RESULT_OK, intencja); finish();
        Toast.makeText(this, pole, Toast.LENGTH_SHORT).show();
    }
}
