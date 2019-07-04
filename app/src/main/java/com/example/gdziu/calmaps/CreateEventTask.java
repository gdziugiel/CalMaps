/**
 * \file CreateEventTask.java
 */
package com.example.gdziu.calmaps;
import android.os.AsyncTask;

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Klasa odpowiedzialna za asynchroniczne dodanie wydarzenia do API Kalendarza.
 *
 */
public class CreateEventTask extends AsyncTask<Void, Void, Void> {
    Calendar mService;
    String summary;
    String location;
    String startDate;

    CreateEventTask(Calendar mService, String summary, String location, String startDate) {
        this.mService = mService;
        this.summary = summary;
        this.location = location;
        this.startDate = startDate;
    }

    @Override
    protected Void doInBackground(Void... params) {
        addCalendarEvent();

        return null;
    }

    public void addCalendarEvent() {
        Event event = new Event()
                .setSummary(summary)
                .setLocation(location);

        DateTime startDateTime = new DateTime(startDate + ".000+02:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setStart(start);

        DateTime endDateTime = new DateTime(startDate + ".000+02:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setEnd(end);

        String calendarId = "primary";
        try {
            mService.events().insert(calendarId, event).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}