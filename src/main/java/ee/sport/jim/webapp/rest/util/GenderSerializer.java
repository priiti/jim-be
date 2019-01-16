package ee.sport.jim.webapp.rest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ee.sport.jim.webapp.domain.shared.Gender;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<Gender> {

	@Override
	public void serialize(Gender gender, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		jsonGenerator.writeString(gender.getGenderResult());
	}
}
