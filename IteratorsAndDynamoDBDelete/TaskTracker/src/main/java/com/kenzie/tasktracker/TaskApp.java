package com.kenzie.tasktracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class TaskApp {

    /**
     * Test your methods on a real database connection. Should print 'null'.
     * @param args main method values
     */
    public static void main(String[] args) {
        //GIVEN
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        Task task = new Task();
        String partitionKey = "e13f1492-a128-49a1-b584-6c26cf620f9c";
        task.setJobId(partitionKey);

        //WHEN
        TaskDAO taskDAO = new TaskDAO(mapper);
        taskDAO.deleteTask(task);

        Task deletedTask = taskDAO.getTask(partitionKey);

        //THEN
        System.out.println("null value: " + deletedTask);
    }
}
