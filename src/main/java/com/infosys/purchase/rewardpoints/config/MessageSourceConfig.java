package com.infosys.purchase.rewardpoints.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

	/**
	 * Method to configure message source
	 * @return MessageSource
	 */
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Base name of message files
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
