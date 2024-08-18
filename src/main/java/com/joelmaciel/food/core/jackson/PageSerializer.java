package com.joelmaciel.food.core.jackson;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        jgen.writeStartObject();

        jgen.writeObjectField("content", page.getContent());
        jgen.writeNumberField("page", page.getNumber());
        jgen.writeNumberField("size", page.getSize());
        jgen.writeNumberField("totalElements", page.getTotalElements());
        jgen.writeNumberField("totalPages", page.getTotalPages());

        jgen.writeEndObject();
    }
}
