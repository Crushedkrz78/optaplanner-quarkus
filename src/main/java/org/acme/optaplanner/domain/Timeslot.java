package org.acme.optaplanner.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Timeslot {
    /**
     * The Timeslot class represents a time interval when lessons are taught, for example:
     * Monday 10:30 - 11:30 or Tuesday 13:30 - 14:30.
     * For simplicity's sake, all time slots have the same duration and there are no time slots during lunch or other breaks.
     * 
     * A time slot has no date, because a High School schedule just repeats every week. So there is no need for 'Continuous Planning'.
     * 
     * Because no Timeslot instances chance during solving, a Timeslot is called a 'Problem Fact'. Such classes do not require any Optaplanner
     * specific annotations.
     * 
     * Notice the 'toString()' method keeps the output short, so it is easier to read OptaPlanner's 'DEBUG' or 'TRACE' log.
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

    public LocalTime getEndTime(){
        return endTime;
    }

    @Override
    public String toString(){
        return dayOfWeek + " " + startTime;
    }
}