/**
 * \file AddEventActivity.java
 */
package com.example.gdziu.calmaps;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import java.util.Calendar;

/** brief Klasa odpowiedzialna za formularz dodania wydarzenia.
 *
 */
public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    EditText txtLocation;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int PLACE_PICKER_REQUEST = 1;

    /** \brief Metoda tworząca activity.
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        txtDate=(EditText)findViewById(R.id.edit_event_date);
        txtTime=(EditText)findViewById(R.id.edit_event_time);
        txtLocation=(EditText)findViewById(R.id.edit_event_place);
        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        txtLocation.setOnClickListener(this);
    }

    /** \brief Metoda uruchamiająca pickery.
     *
     */
    @Override
    public void onClick(View v) {

        if (v == txtDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String sDayOfMonth;
                            if(dayOfMonth < 10) {
                                sDayOfMonth = "0" + dayOfMonth;
                            }
                            else {
                                sDayOfMonth = String.valueOf(dayOfMonth);
                            }
                            String sMonth;
                            if(monthOfYear + 1 < 10) {
                                sMonth = "0" + (monthOfYear + 1);
                            }
                            else {
                                sMonth = String.valueOf(monthOfYear + 1);
                            }
                            txtDate.setText(year + "-" + sMonth + "-" + sDayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == txtTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String sHourOfDay;
                            if(hourOfDay < 10) {
                                sHourOfDay = "0" + hourOfDay;
                            }
                            else {
                                sHourOfDay = String.valueOf(hourOfDay);
                            }
                            String sMinute;
                            if(minute < 10) {
                                sMinute = "0" + minute;
                            }
                            else {
                                sMinute = String.valueOf(minute);
                            }
                            txtTime.setText(sHourOfDay + ":" + sMinute + ":00");
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == txtLocation) {

            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            try {
                startActivityForResult(builder.build(AddEventActivity.this), PLACE_PICKER_REQUEST);
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
        }
    }

    /** \brief Metoda wysyłająca dane z formularza do MainActivity.
     *
     */
    public void sendMessage(View view) {
        EditText name = (EditText) findViewById(R.id.edit_event_name);
        EditText place = (EditText) findViewById(R.id.edit_event_place);
        EditText date = (EditText) findViewById(R.id.edit_event_date);
        EditText time = (EditText) findViewById(R.id.edit_event_time);
        String summary = name.getText().toString();
        String location = place.getText().toString();
        String startDate = date.getText().toString() + "T" + time.getText().toString();
        //Intent intencja = new Intent();
        //intencja.putExtra("wpis", pole);
        Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
        intent.putExtra("summary", summary);
        intent.putExtra("location", location);
        intent.putExtra("startDate", startDate);
        startActivity(intent);
        //setResult(RESULT_OK, intencja); finish();
        //Toast.makeText(this, startDate, Toast.LENGTH_SHORT).show();
    }

    /** \brief Metoda odbierająca dane z place pickera.
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("LATITUDE: ");
                stringBuilder.append(latitude);
                stringBuilder.append("LONGITUDE: ");
                stringBuilder.append(longitude);
                txtLocation.setText(stringBuilder.toString());
            }
        }
    }
}
