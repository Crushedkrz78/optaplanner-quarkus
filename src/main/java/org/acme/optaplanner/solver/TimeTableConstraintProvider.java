package org.acme.optaplanner.solver;

import org.acme.optaplanner.domain.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class TimeTableConstraintProvider  implements ConstraintProvider{
    /**
     * A Score represents the quality of a given solution. The Higher, the better.
     * Optplanner looks for the best solution, which is the solution with the highest score found
     * in the available time. It could be the 'optimal' solution.
     * This use case has 'Hard' and 'Soft' constraints, so it uses the HardSoftScore class
     * to represent the score.
     * 
     * - Hard constraint *MUST* not be broken.
     * 
     * - Soft constraints *SHOULD* not be broken.
     * 
     * Hard constraints are weighted against other hard constraints. Soft constraints are weighted too,
     * against ither soft constraints.
     * 
     * Hard constraints always outweigh soft constraints, regardless of their respective weights.
     * 
     * 
     * This class uses Optaplanner's ConstraintStream API which is inspired by Java 8 Streams and SQL.
     */

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory){
        return new Constraint[]{
            // Hard Constraints
            roomConflict(constraintFactory),
            teacherConflict(constraintFactory),
            studentGroupConflict(constraintFactory),
            // Soft constraints are only implemented in optaplanner-quickstart
        };
    }

    private Constraint roomConflict(ConstraintFactory constraintFactory){
        // A room can accommodat at most one lesson at the same time.

        // Select a Lesson...
        return constraintFactory.from(Lesson.class)
            // ... and pair it with another lesson ...
            .join(Lesson.class,
                    // ... in the same timeslot ...
                    Joiners.equal(Lesson::getTimeslot),
                    // ... in the asme room ...
                    Joiners.equal(Lesson::getRoom),
                    // ... and the pair is unique (different id, no reverse pairs)
                    Joiners.lessThan(Lesson::getId))
                    // then penalize each pair with a hard weight
                    .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint teacherConflict(ConstraintFactory constraintFactory){
        // A teacher can teach at most one lesson ath the same time.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getTeacher))
                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint studentGroupConflict(ConstraintFactory constraintFactory){
        // A student can attend at most one lesson at the same time.
        return constraintFactory
                .fromUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getStudentGroup))
                .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }    
}