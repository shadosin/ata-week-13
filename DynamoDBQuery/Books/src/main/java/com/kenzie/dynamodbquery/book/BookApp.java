package com.kenzie.dynamodbquery.book;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.dynamodbquery.book.utilities.DynamoDbClientProvider;

import java.util.List;

public class BookApp {

    /**
     * Test your methods on a real database connection. Should print [The Book Thief, to Kill a Mockingbird, My Side of
     *                 the Mountain].
     * @param args main method arguments
     */
    public static void main(String[] args) {
        //GIVEN (1st query call)
        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        String employeeId = "5b2ccd28";
        String exclusiveStartAsin = null;
        int limit = 2;

        //WHEN (1st query call)
        BookDAO bookDAO = new BookDAO(mapper);
        BookHelperMethods helperMethods = new BookHelperMethods();
        List<Book> queryBookList = bookDAO.getBooksReadByEmployee(employeeId, exclusiveStartAsin, limit);

        //THEN (1st query call)
        System.out.println("The first query call when limit = 2 retrieves the 2 books with titles The Book Thief and " +
                "To Kill a Mockingbird: " + helperMethods.getTitlesFromList(queryBookList).toString());

        //GIVEN (2nd query call)
        exclusiveStartAsin = "A456HGF872";

        //WHEN (2nd query call)
        queryBookList = bookDAO.getBooksReadByEmployee(employeeId, exclusiveStartAsin, limit);

        //THEN (2nd query call)
        System.out.println("The second query call when limit = 2 retrieves the book with title My Side of the " +
                "Mountain: " + helperMethods.getTitlesFromList(queryBookList).toString());
    }
}
