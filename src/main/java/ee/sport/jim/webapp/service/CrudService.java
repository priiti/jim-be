package ee.sport.jim.webapp.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

	List<T> findAll();

	Optional<T> findById(ID id);

	T save(T object);

	void delete(T object);

	void deleteBy(ID id);

}
