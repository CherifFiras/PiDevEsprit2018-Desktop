/**
 * Copyright (C) 2015, 2016 Dirk Lemmermann Software & Consulting (dlsc.com) 
 * 
 * This file is part of CalendarFX.
 */

package Controller;

import Core.Controller;
import IService.IEvenementService;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import java.sql.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.calendarfx.model.Entry;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CalendarApp extends Controller {

    private final IEvenementService es = this.getService().getEvenementService();

    public static StackPane startCalendar() throws Exception {
        CalendarView calendarView = new CalendarView();

        Calendar accepted = new Calendar("Accepted");


        accepted.setShortName("Accepted");
        loadCalander(accepted);
        
        EventHandler<CalendarEvent> handler = evt -> foo(evt);
        accepted.addEventHandler(handler);
        accepted.setStyle(Style.STYLE1);
   
        
        CalendarSource familyCalendarSource = new CalendarSource("Family");
        familyCalendarSource.getCalendars().addAll( accepted);

        calendarView.getCalendarSources().setAll(familyCalendarSource);
        calendarView.setRequestedTime(LocalTime.now());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(calendarView);
        
        
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

     
        return stackPane;
    }

private EventHandler<CalendarEvent> foo(CalendarEvent evt) {
        System.out.println("NEW" + evt.getEntry().getCalendar().getName());
        System.out.println("OLD" + evt.getOldCalendar().getName());

        if (evt.getEntry().getCalendar().getName() != evt.getOldCalendar().getName()) {
            es.updateStatus(evt.getEntry().getCalendar().getName(), Integer.parseInt(evt.getEntry().getId()));
        }
        int id = Integer.parseInt(evt.getEntry().getId());
        if (evt.getOldInterval().getStartDate() != evt.getEntry().getStartDate() || evt.getOldInterval().getEndDate() != evt.getEntry().getEndDate()) {
            LocalDate startDate = evt.getEntry().getInterval().getStartDate();
            LocalDate endDate = evt.getEntry().getInterval().getStartDate();
            es.updateIntervalle(startDate.atStartOfDay(), endDate.atStartOfDay(), id);
        }
        if (evt.ENTRY_TITLE_CHANGED.toString() == "ENTRY_TITLE_CHANGED") {
            String title = evt.getEntry().getTitle();
            es.update(new Event(title, id));

        }

        if (evt.isEntryAdded()) {
            Entry e = evt.getEntry();
            es.add(new Event(e.getTitle(), 0, Date.valueOf(e.getStartDate()), Date.valueOf(e.getEndDate()), 0, 0, "Pending", 0.0));
        }

        return (event) -> {
            if (evt.isEntryRemoved()) {
                es.delete(id);
            }
        };
    }


    private static void loadCalander(Calendar accepted) {
                es.getEv()
                .forEach(e -> {
                    String titre = e.getTitre();
                    int id = e.getId();
                    Date Date = e.getDateEvenement();
                  
                    
                    Entry entry = new Entry(titre, Date);
                    entry.setId(Integer.toString(id));
                    e.getStatus().equals("Accept") {
                        accepted.addEntry(entry);
                        entry.setCalendar(accepted);
                    }
    }

                            }

   
}
