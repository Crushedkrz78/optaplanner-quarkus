package org.acme.optaplanner.rest;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.optaplanner.domain.TimeTable;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;

@Path("/timeTable")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeTableResource {
    /**
     * Solving planning probles on REST threads may cause HTTP timeout issues.
     * therefore, the Quarkus extension injects a 'SolverManager', which runs solvers
     * in a separate thread pool and can solve multiple datasets in parallel.
     */

    @Inject
    SolverManager<TimeTable, UUID> solverManager;

    @POST
    @Path("/solve")
    public TimeTable solve(TimeTable problem){
        UUID problemId = UUID.randomUUID();

        // Submit the problem to start solving
        SolverJob<TimeTable, UUID> solverJob = solverManager.solve(problemId, problem);
        TimeTable solution;
        try{
            // Wait until the solving ends
            solution = solverJob.getFinalBestSolution();
        } catch(InterruptedException | ExecutionException e){
            throw new IllegalStateException("Solving failed. ", e);
        }

        return solution;
    }
}