package org.acme.optaplanner.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class TimeTable {
    /**
     * A 'TimeTable' wraps all 'Timeslot', 'Room' and 'Lesson' instances of a single dataset.
     * Furthermore, because it contains all lessons, each with a specific variable set, 
     * it is a 'Planning Solution' and it has a score:
     *  - If lessons are still unassigned.
     *  - If it breaks hard constraints, then is an 'infeasible' solution.
     *  - If it adheres to all hard constraints, then it is a 'feasible' solution.
     * 
     * The 'TimeTable' class hass an @PlanningSolution annotation, so Optaplanner knows that this class
     * contains all of the input and output data.
     * 
     * Specifically, this class is the input of the problem:
     *  - A 'timeslotList' field with all time slots.
     *    - This is a list of problem facts, because they do not change during solving.
     *  - A 'roomList' field with all rooms:
     *    - This is a list of problem facts, because they do not change during solving.
     *  - A 'lessonList' field with all lessons:
     *    - This is a list of planning entities, because they change during solving.
     *    - Of each 'Lesson':
     *      - The values of the 'timeslot' and 'room' fields are tipically still 'null',
     *        so 'unassigned'. They are planning variables.
     *      - The other fields, such as 'subject', 'teacher' and 'studentGroup', are filled in.
     *        These fields are problem properties.
     * 
     * However, this class is also the output of the solution:
     *  - A 'lessonList' field for which each 'Lesson' instance has non-null 'timeslot' and 'room'
     *    fields after solving.
     *  - A 'score' field that represents the quality of the output solution, for example:
     *    0hard/-5soft
     * 
     * The 'timeslotList' field is a value range provider. It holds the 'Timeslot' instances which
     * Optaplanner can pick from to assign to the 'timeslot' field of 'Lesson' instances.
     * The 'timeslotList' field has an @ValueRangeProvider annotation to connect those two,
     * by matching the 'id' with the 'valueRangeProviderRefs' of the @PlanningVariable
     * in the 'Lesson'.
     * 
     * Furthermore, Optaplanner need to know which 'Lesson' instances it can change as well as how
     * to retrieve the 'Timeslot' and 'Room' instances used for score calculation by your
     * 'TimeTableConstraintProvider'.
     * 
     * The 'timeslotList' and 'roomList' fields have an @ProblemFactCollectionProperty annotation,
     * so your 'TimeTableConstraintProvider' can select -from- those too.
     * 
     * The 'lessonList' has an @PlanningEntityCollectionProperty annotation, so Optaplanner can
     * change them during solving and your 'TimeTableConstraintProvider' can select -from- those too.
     */
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<Timeslot> timeslotList;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "roomRange")
    private List<Room> roomList;

    @PlanningEntityCollectionProperty
    private List<Lesson> lessonList;

    @PlanningScore
    private HardSoftScore score;

    public TimeTable(){}

    public TimeTable(List<Timeslot> timeslotList, List<Room> roomList, List<Lesson> lessonList){
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.lessonList = lessonList;
    }

    public List<Timeslot> getTimeslotList(){
        return timeslotList;
    }

    public List<Room> getRoomList(){
        return roomList;
    }

    public List<Lesson> getLessonList(){
        return lessonList;
    }

    public HardSoftScore getScore(){
        return score;
    }
}