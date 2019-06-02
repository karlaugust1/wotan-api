package br.com.wotan.converters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@Component
public class JsonDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    public JsonDateTimeSerializer(){
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	String formattedString = value.format(formatter);
    	
    	gen.writeString(formattedString);
    }
}	