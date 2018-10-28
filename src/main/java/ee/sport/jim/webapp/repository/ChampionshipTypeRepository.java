package ee.sport.jim.webapp.repository;

import ee.sport.jim.webapp.domain.ChampionshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionshipTypeRepository extends JpaRepository<ChampionshipType, Long> {

	Optional<ChampionshipType> findByName(String name);

}
