package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.Authority;
import ee.sport.jim.webapp.domain.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Optional<Authority> findByName(AuthorityName name);

}
