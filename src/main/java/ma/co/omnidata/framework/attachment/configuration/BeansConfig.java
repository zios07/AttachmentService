package ma.co.omnidata.framework.attachment.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClientOptions;

@Configuration
public class BeansConfig {
	
	@Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder()
        		.socketTimeout(1000)
        		.connectTimeout(1000)
        		.serverSelectionTimeout(4000)
        		.maxWaitTime(1000)
        		.build();
    }
	
}
