package ee.sport.jim.webapp.rest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ee.sport.jim.system.core.BooleanHolder;

import java.io.IOException;

public class BooleanSerializer extends JsonSerializer<BooleanHolder> {

	@Override
	public void serialize(BooleanHolder booleanHolder, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		jsonGenerator.writeBoolean(booleanHolder.isValue());
	}
}
