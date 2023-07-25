package com.kenzie.narrowing;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.narrowing.dependency.DynamoDbClientProvider;

import java.util.List;


public class LogApp {

    /**
     * Test your methods on a real database connection. Should print the following—
     * first test: [20200109-15:28:18, 20200109-16:45:01, 20200109-20:17:05, 20200112-12:15:36, 20200113-08:47:56]
     * second test: [20200109-12:15:36, 20200109-12:15:42, 20200109-15:28:18, 20200109-16:45:01]
     * third test: [20200112-12:15:36, 20200113-08:47:56, 20200113-09:25:45, 20200113-09:48:15, 20200113-10:27:48].
     * @param args main method arguments
     */
    public static void main(String[] args) {
        //GIVEN (between times)
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        String partitionKey = "INFO";
        String startTime = "20200109-15:01:01";
        String endTime = "20200113-09:01:01";

        //WHEN (between times)
        LogDAO logDAO = new LogDAO(mapper);
        LogHelperMethods helperMethods = new LogHelperMethods();
        List<Log> queryBetweenTimes = logDAO.getLogsBetweenTimes(partitionKey, startTime, endTime);

        //THEN (between times)
        System.out.println("The time stamps for the 5 Logs retrieved from querying the times between " + startTime +
                " and " + endTime + " are: " + helperMethods.getTimeStampsFromList(queryBetweenTimes));

        //GIVEN (before time)
        endTime = "20200109-16:59:59";

        //WHEN (before time)
        List<Log> queryBeforeTime = logDAO.getLogsBeforeTime(partitionKey, endTime);

        //THEN (before time)
        System.out.println("The time stamps for the 4 Logs retrieved from querying the times before " + endTime +
                " are: " + helperMethods.getTimeStampsFromList(queryBeforeTime));

        //GIVEN (after time)
        startTime = "20200112-01:01:01";

        //WHEN (after time)
        List<Log> queryAfterTime = logDAO.getLogsAfterTime(partitionKey, startTime);

        //THEN (after time)
        System.out.println("The time stamps for the 5 Logs retrieved from querying the times after " + startTime +
                " are: " + helperMethods.getTimeStampsFromList(queryAfterTime));
    }
}
