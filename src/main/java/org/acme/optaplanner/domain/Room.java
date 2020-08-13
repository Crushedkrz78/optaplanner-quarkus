package org.acme.optaplanner.domain;

public class Room {
    /**
     * The Room class represents a location where lessons are taught, form example:
     * Room A or Room B
     * For simplicity's sake, all rooms are without capacity limits and the can acommodate all lessons.
     * 
     * Room instances do not chance during solving, so Room is a 'Problem Fact'.
     */

    private String name;

    public Room(){}

    public Room(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
    
}