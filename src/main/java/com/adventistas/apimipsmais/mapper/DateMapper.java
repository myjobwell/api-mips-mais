package com.adventistas.apimipsmais.mapper;

import org.mapstruct.Mapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(componentModel = "spring")
public class DateMapper {

    public OffsetDateTime toOffsetDateTime(Date date) {
        if (date == null) return null;
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

    public Date toDate(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) return null;
        return Date.from(offsetDateTime.toInstant());
    }
}
