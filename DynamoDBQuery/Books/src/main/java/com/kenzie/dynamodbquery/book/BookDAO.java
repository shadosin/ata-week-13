package com.kenzie.dynamodbquery.book;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Book book = new Book();
        book.setId(employeeId);

        DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
                .withHashKeyValues(book);
        PaginatedQueryList<Book> bookList = mapper.query(Book.class, queryExpression);

        return bookList;
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
        Book book = new Book();
        book.setId(employeeId);

        Map<String, AttributeValue> exclusiveStartKey = null;
        if (exclusiveStartAsin != null){
            exclusiveStartKey = new HashMap<>();
            exclusiveStartKey.put("id", new AttributeValue().withS(employeeId));
            exclusiveStartKey.put("asin", new AttributeValue().withS(exclusiveStartAsin));
        }
        DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
                .withHashKeyValues(book)
                .withExclusiveStartKey(exclusiveStartKey)
                .withLimit(limit);

        QueryResultPage<Book> employeeBooksList = mapper.queryPage(Book.class, queryExpression);
        return employeeBooksList.getResults();
    }
}
