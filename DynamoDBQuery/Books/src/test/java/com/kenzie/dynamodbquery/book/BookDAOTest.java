package com.kenzie.dynamodbquery.book;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookDAOTest {

    @InjectMocks
    private BookDAO bookDao;

    @Mock
    DynamoDBMapper mapper;

    @Mock
    PaginatedQueryList<Book> books;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getBooksReadByEmployee_employeeHasReadBooks_returnsBooks() {
        //GIVEN
        String employeeId = "5b2ccd28";

        // GIVEN
        when(mapper.query(eq(Book.class), any(DynamoDBQueryExpression.class))).thenReturn(books);
        ArgumentCaptor<DynamoDBQueryExpression<Book>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        // WHEN
        List<Book> result = bookDao.getBooksReadByEmployee(employeeId);

        // THEN
        assertEquals(books, result, "Expected list of books to be what was returned from DynamoDB");
        verify(mapper).query(eq(Book.class), captor.capture());
        Book queriedBook = captor.getValue().getHashKeyValues();
        assertEquals(employeeId, queriedBook.getId(), "Expected query expression to query for " +
            "partition key: " + employeeId);

    }

}
