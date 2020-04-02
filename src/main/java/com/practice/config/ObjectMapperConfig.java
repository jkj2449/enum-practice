package com.practice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class ObjectMapperConfig {

    private static final String YYYYMMDD = "yyyyMMdd";
    private static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_YYYYMMDDHHMISS = DateTimeFormatter.ofPattern(YYYYMMDDHHMISS, Locale.KOREAN);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_YYYYMMDD = DateTimeFormatter.ofPattern(YYYYMMDD, Locale.KOREAN);

	@Bean
	public ObjectMapper objectMapper() {
        final ObjectMapper MAPPER = new ObjectMapper();

        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        MAPPER.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        MAPPER.registerModule(createJavaTimeModule());
        return MAPPER;
    }

	private JavaTimeModule createJavaTimeModule() {
		JavaTimeModule module = new JavaTimeModule();
		module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER_YYYYMMDDHHMISS));
		module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER_YYYYMMDDHHMISS));
		module.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_TIME_FORMATTER_YYYYMMDD));
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_TIME_FORMATTER_YYYYMMDD));

		return module;
	}
}
