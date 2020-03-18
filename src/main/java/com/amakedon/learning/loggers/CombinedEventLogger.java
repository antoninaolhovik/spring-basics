package com.amakedon.learning.loggers;

import com.amakedon.learning.beans.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {

    private final List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }
}
