package ee.sport.jim.webapp.rest.dto.converter;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericConverter<E, D> {

	public abstract D convertEntity(E entity);

	public abstract E convertDto(D dto);

	public List<E> convertDto(final List<D> dtos) {
		List<E> result = new ArrayList<>();
		for (D dto : dtos) {
			result.add(convertDto(dto));
		}
		return result;
	}

	public List<D> convertEntity(List<E> entities) {
		List<D> result = new ArrayList<>();
		for (E entity : entities) {
			result.add(convertEntity(entity));
		}
		return result;
	}
}
