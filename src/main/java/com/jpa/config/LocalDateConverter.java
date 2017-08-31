package com.jpa.config;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

/**
 * Created by java on 2017.08.31..
 */
public class LocalDateConverter implements AttributeConverter<LocalDate,Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        return Optional.ofNullable(attribute)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date attributeDate) {
        return Optional.ofNullable(attributeDate)
                .map(Date::toLocalDate)
                .orElse(null);
    }
}
