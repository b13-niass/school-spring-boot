package org.example.odc.config;

import org.example.odc.web.filter.ResponseFormattingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public Filter responseFormattingFilter() {
        return new ResponseFormattingFilter();
    }
}
