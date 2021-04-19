package com.pierre.oceanblu.config;

import com.pierre.oceanblu.converter.StringToGenderConverter;
import com.pierre.oceanblu.converter.StringToRoleConverter;
import com.pierre.oceanblu.converter.StringToStatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new StringToGenderConverter());
        registry.addConverter(new StringToRoleConverter());
        registry.addConverter(new StringToStatusConverter());
    }
}
