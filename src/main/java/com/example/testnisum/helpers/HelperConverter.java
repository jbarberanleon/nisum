package com.example.testnisum.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelperConverter implements AttributeConverter<Map<String, Object>, String> {
    private static final Logger logger = LoggerFactory.getLogger(HelperConverter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }
        return result;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        Map<String, Object> result = null;
        try {
            result = objectMapper.readValue(dbData,
                    new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return result;
    }
}
