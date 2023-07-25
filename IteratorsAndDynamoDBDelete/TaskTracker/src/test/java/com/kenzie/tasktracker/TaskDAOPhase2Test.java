package com.kenzie.tasktracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskDAOPhase2Test {

    @Mock
    DynamoDBMapper mapper;

    private String yearToDelete = "2016";

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void deleteTaskFromSpecificYear_validItem_returnsSuccessfulDelete() {
        //GIVEN
        Task task = new Task();
        String partitionKey = "JO9457";
        task.setJobId(partitionKey);
        task.setCompletedBy("Li Juan");
        task.setYearCompleted(2016);
        task.setHoursTaken(5);
        doNothing().when(mapper).delete(eq(task), (DynamoDBDeleteExpression) any());
        when(mapper.load(Task.class, partitionKey)).thenReturn(null);

        //WHEN
        TaskDAO taskDAO = new TaskDAO(mapper);
        taskDAO.deleteTask(task, yearToDelete);

        Task deletedTask = taskDAO.getTask(partitionKey);

        //THEN
        verify(mapper, times(1)).delete(eq(task), (DynamoDBDeleteExpression) any());
        assertEquals(null, deletedTask, "The task with job_id 'JO9457' should have been deleted and " +
                "so  attempting to load the item should return 'null'");
    }

    @Test
    public void deleteTask_invalidItem_returnsUnsuccessfulDelete() {
        //GIVEN
        Task task = new Task();
        String partitionKey = "JO1154";
        task.setJobId(partitionKey);
        task.setCompletedBy("Martha Rivera");
        task.setHoursTaken(8);
        task.setYearCompleted(2019);
        doNothing().when(mapper).delete(eq(task), (DynamoDBDeleteExpression) any());
        when(mapper.load(Task.class, partitionKey)).thenReturn(task);

        //WHEN
        TaskDAO taskDAO = new TaskDAO(mapper);
        taskDAO.deleteTask(task, yearToDelete);

        Task notDeletedTask = taskDAO.getTask(partitionKey);

        //THEN
        verify(mapper, times(1)).delete(eq(task), (DynamoDBDeleteExpression) any());
        assertEquals("JO1154", notDeletedTask.getJobId(), "The task with job_id 'JO1154' should not " +
                "have been deleted and so loading the item returns an item with job_id 'JO1154'");
    }
}
