package com.kenzie.dynamodbquery.book;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.dynamodbquery.book.Book;
import com.kenzie.dynamodbquery.book.BookDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookDAO2Test {

    @InjectMocks
    private BookDAO bookDao;

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private QueryResultPage queryResult;

    @Mock
    private List books;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getBooksReadByEmployee_exclusiveStartNull_returnsBooks() {
        // GIVEN
        String employeeId = "employeeId";
        int limit = 2;
        when(mapper.queryPage(eq(Book.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);
        when(queryResult.getResults()).thenReturn(books);
        ArgumentCaptor<DynamoDBQueryExpression<Book>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        // WHEN
        List<Book> result = bookDao.getBooksReadByEmployee(employeeId, null, limit);

        // THEN
        assertEquals(result, books, "Expected list of books to be what was returned by DynamoDB");
        verify(mapper).queryPage(eq(Book.class), captor.capture());
        Book queriedBook = captor.getValue().getHashKeyValues();
        Map<String, AttributeValue> queriedExclusiveStartKey = captor.getValue().getExclusiveStartKey();
        int queriedLimit = captor.getValue().getLimit();
        assertEquals(employeeId, queriedBook.getId(), "Expected query expression to query for partition key: " +
            employeeId);
        assertNull(queriedExclusiveStartKey, "Expected query expression to not include an exclusive start key");
        assertEquals(limit, queriedLimit, "Expected query expression to query with limit " + limit);
    }

    @Test
    public void getBooksReadByEmployee_exclusiveStartNotNull_returnsBooks() {
        // GIVEN
        String employeeId = "employeeId";
        String exclusiveStartAsin = "asin";
        int limit = 2;
        when(mapper.queryPage(eq(Book.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);
        when(queryResult.getResults()).thenReturn(books);
        ArgumentCaptor<DynamoDBQueryExpression<Book>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        // WHEN
        List<Book> result = bookDao.getBooksReadByEmployee(employeeId, exclusiveStartAsin, limit);

        // THEN
        assertEquals(result, books, "Expected list of books to be what was returned by DynamoDB");
        verify(mapper).queryPage(eq(Book.class), captor.capture());
        Book queriedBook = captor.getValue().getHashKeyValues();
        Map<String, AttributeValue> queriedExclusiveStartKey = captor.getValue().getExclusiveStartKey();
        int queriedLimit = captor.getValue().getLimit();
        assertEquals(employeeId, queriedBook.getId(), "Expected query expression to query for partition key: " +
            employeeId);
        assertEquals(exclusiveStartAsin, queriedExclusiveStartKey.get("asin").getS(), "Expected query expression to " +
            "query for exclusive start key asin with value " + exclusiveStartAsin);
        assertEquals(limit, queriedLimit, "Expected query expression to query with limit " + limit);
    }
}
