package com.kenzie.narrowing;

import java.util.ArrayList;
import java.util.List;

public class LogHelperMethods {

    /**
     * Takes a list of Log entries and creates a list of just the time stamps to make it easier to print values for
     * testing.
     * @param logList the given list of Log entries
     * @return the list of Log time stamps
     */
    public List<String> getTimeStampsFromList(List<Log> logList) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < logList.size(); i++) {
            newList.add(logList.get(i).getTimeStamp());
        }
        return newList;
    }
}
