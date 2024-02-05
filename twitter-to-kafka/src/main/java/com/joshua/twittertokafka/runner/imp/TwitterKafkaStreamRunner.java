package com.joshua.twittertokafka.runner.imp;

import com.joshua.twittertokafka.config.TwitterToKafkaServiceConfigData;
import com.joshua.twittertokafka.listener.TwitterKafkaStatusListener;
import com.joshua.twittertokafka.runner.StreamRunner;
import jakarta.annotation.PreDestroy;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

//@Component
@RequiredArgsConstructor
//@ConditionalOnProperty(name = "twitter-to-kafka-service.enable-v2-tweets", havingValue = "false")
@ConditionalOnExpression("${twitter-to-kafka-service.enable-mock-tweets} && not ${twitter-to-kafka-service.enable-v2-tweets}")
public class TwitterKafkaStreamRunner implements StreamRunner {

    private final Logger Log=org.slf4j.LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);


    public final TwitterToKafkaServiceConfigData data;
    private final TwitterKafkaStatusListener listener;
    private  TwitterStream streams;

    @Override
    public void start() throws TwitterException {

        streams = new TwitterStreamFactory().getInstance();
        streams.addListener(listener);
        addFilter();
    }
@PreDestroy
public void shutdown(){
    if (streams != null) {
        Log.info("close twitter stream");
        streams.shutdown();
    }
}

    private void addFilter() {
        String[] keywords = data.getTwitterKeywords().toArray(new String[0]);
        FilterQuery query = new FilterQuery(keywords);
        streams.filter(query);
        Log.info(Arrays.toString(keywords));
    }
}
