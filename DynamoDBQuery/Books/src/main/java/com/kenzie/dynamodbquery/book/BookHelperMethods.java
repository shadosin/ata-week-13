package com.kenzie.dynamodbquery.book;

import java.util.ArrayList;
import java.util.List;

public class BookHelperMethods {

    /**
     * Takes a list of Book items and creates a list of just the titles to make it easier to print values for testing.
     * @param bookList the given list of Books items
     * @return the list of Book titles
     */
    public List<String> getTitlesFromList(List<Book> bookList) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            newList.add(bookList.get(i).getTitle());
        }
        return newList;
    }
}
