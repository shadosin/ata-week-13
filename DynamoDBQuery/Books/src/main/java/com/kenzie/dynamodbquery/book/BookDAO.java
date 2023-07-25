package com.kenzie.dynamodbquery.book;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.List;

public class BookDAO {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Book objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public BookDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Uses the query() method to retrieve all the items from the BooksRead table that have a given employee id value.
     * @param employeeId the employee ID
     * @return the PaginatedQueryList that is returned from the query
     */
    public List<Book> getBooksReadByEmployee(String employeeId) {
        //TODO: implement
        return null;
    }

    /**
     * Uses the query() method to retrieve all the items from the BooksRead table that have a given employee id value.
     * @param employeeId the employee ID
     * @param exclusiveStartAsin the Asin to begin at
     * @param limit the number of results returned each time
     * @return the List from the QueryResultPage that is returned from the query
     */
    public List<Book> getBooksReadByEmployee(String employeeId, String exclusiveStartAsin, int limit){
        //TODO: implement
        return null;
    }
}
