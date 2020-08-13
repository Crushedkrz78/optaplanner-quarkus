package org.acme.optaplanner.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

public class Lesson {
    /**
     * During a Lesson, represented by the Lesson class, a teacher teaches a subject to a group of students, for example:
     * Math by A.Turing for 9th Grade
     * Chemistry by M. Curie for 10th Grade
     * If a subject is taught multiple times per week by the same student group, there are multiple Lesson instances
     * that are only distinguishable by 'Id'. For example:
     * 9th Grade has six math lessons per week.
     * 
     * During solving, Optaplanner changes the 'timeslot' and 'room' fields of the 'Lesson' class, to assign each lesson
     * to a time slot and a room. Because Optaplanner changes these fields, 'Lesson' is a 'Planning Entity'.
     * 
     * Most of the fields in 'Lesson' class contain Input Data, excep for 'timeslot' and 'room' fields.
     * Optaplanner changes these fields during solving. Such fields are called 'Plannning Variables'. In order for
     * Optaplanner to recognize them, both 'timeslot' and 'room' fields require a @PlanningVariable annotation.
     * Their containing class, 'Lesson', requires an @PlanningEntity annotation.
     */

     @PlanningId
     private Long id;

     private String subject;
     private String teacher;
     private String studentGroup;

     @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
     private Timeslot timeslot;

     @PlanningVariable(valueRangeProviderRefs = "roomRange")
     private Room room;

     public Lesson(){}

     public Lesson(Long id, String subject, String teacher, String studentGroup){
         this.id = id;
         this.subject = subject;
         this.teacher = teacher;
         this.studentGroup = studentGroup;
     }

     public Long getId(){
         return id;
     }

     public String getSubject(){
         return subject;
     }

     public String getTeacher(){
         return teacher;
     }

     public String getStudentGroup(){
         return studentGroup;
     }

     public Timeslot getTimeslot(){
         return timeslot;
     }

     public void setTimeslot(Timeslot timeslot){
         this.timeslot = timeslot;
     }

     public Room getRoom(){
         return room;
     }

     @Override
     public String toString(){
         return subject + "(" + id + ")";
     }
    
}