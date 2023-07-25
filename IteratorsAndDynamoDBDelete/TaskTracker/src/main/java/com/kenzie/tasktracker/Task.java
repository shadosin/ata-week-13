package com.kenzie.tasktracker;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "TaskTracker")
public class Task {
    private String jobId;
    private String completedBy;
    private Integer yearCompleted;
    private Integer hoursTaken;

    @DynamoDBHashKey(attributeName = "job_id")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @DynamoDBAttribute(attributeName = "completed_by")
    public String getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }

    @DynamoDBAttribute(attributeName = "year_completed")
    public Integer getYearCompleted() {
        return yearCompleted;
    }

    public void setYearCompleted(Integer yearCompleted) {
        this.yearCompleted = yearCompleted;
    }

    @DynamoDBAttribute(attributeName = "hours_taken")
    public Integer getHoursTaken() {
        return hoursTaken;
    }

    public void setHoursTaken(Integer hoursTaken) {
        this.hoursTaken = hoursTaken;
    }
}
