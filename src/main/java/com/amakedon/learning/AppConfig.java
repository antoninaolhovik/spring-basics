package com.amakedon.learning;

import com.amakedon.learning.beans.Client;
import com.amakedon.learning.beans.Event;
import com.amakedon.learning.beans.EventType;
import com.amakedon.learning.loggers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public Client client() {
        return new Client("1", "John Smith");
    }

    @Bean
    public EventLogger eventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public App app() {
        Map<EventType, EventLogger> loggersMap = new HashMap<>();
        loggersMap.put(EventType.INFO, eventLogger());
        loggersMap.put(EventType.ERROR, combinedEventLogger());
        return new App(client(), eventLogger(), loggersMap);
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), dateFormat());
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("./test.txt");
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("./test.txt", 3);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(eventLogger(), fileEventLogger()));
    }


}
