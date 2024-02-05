package com.joshua.twittertokafka;

import com.joshua.twittertokafka.config.TwitterToKafkaServiceConfigData;
import com.joshua.twittertokafka.runner.StreamRunner;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RequiredArgsConstructor
//@ComponentScan(basePackages = "com.joshua.twittertokafka")
public class TwitterToKafkaApplication implements CommandLineRunner {

    private final Logger LOG = org.slf4j.LoggerFactory.getLogger(TwitterToKafkaApplication.class);

    private final TwitterToKafkaServiceConfigData data;
    private final StreamRunner runner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        LOG.info(Arrays.toString(data.getTwitterKeywords().toArray(new String[]{})));
//        LOG.info(String.format("%s", data.getWelcomeMessage()));
        runner.start();
    }
}
