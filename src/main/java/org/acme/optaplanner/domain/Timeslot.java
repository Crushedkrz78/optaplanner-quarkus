package org.acme.optaplanner.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Timeslot {
    /**
     * The Timeslot class represents a time interval when lessons are taught
     */

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public Timeslot(){}

    public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime){
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek(){
        return dayOfWeek;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalTime getEntTime(){
        return endTime;
    }

    @Override
    public String toString(){
        return dayOfWeek + " " + startTime;
    }
}