package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.Role;
import ee.sport.jim.webapp.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName name);

}
