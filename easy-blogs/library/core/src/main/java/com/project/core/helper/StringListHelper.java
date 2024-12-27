package com.project.core.helper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Converter
@Component
public class StringListHelper implements AttributeConverter<List<String>, String>{

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? String.join(SPLIT_CHAR, strings) : StringUtils.EMPTY;
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
    }
}
