package com.amakedon.learning.loggers;

import com.amakedon.learning.beans.Event;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
