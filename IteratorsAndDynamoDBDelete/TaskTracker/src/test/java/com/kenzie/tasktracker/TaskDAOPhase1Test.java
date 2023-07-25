package com.kenzie.tasktracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskDAOPhase1Test {

    @Mock
    DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void deleteTask_withJobIdJO7895_returnsNull() {
        //GIVEN
        Task task = new Task();
        String partitionKey = "JO7895";
        task.setJobId(partitionKey);
        task.setCompletedBy("Arnaz Desai");
        task.setHoursTaken(10);
        task.setYearCompleted(2017);
        doNothing().when(mapper).delete(task);

        //WHEN
        TaskDAO taskDAO = new TaskDAO(mapper);
        taskDAO.deleteTask(task);

        Task deletedTask = taskDAO.getTask(partitionKey);

        //THEN
        verify(mapper, times(1)).delete(task);
        assertEquals(null, deletedTask, "The task with job_id 'JO7895' should have been deleted and so" +
                "attempting to load the item should return 'null'");
    }
}
