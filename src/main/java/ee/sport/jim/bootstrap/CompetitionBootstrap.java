package ee.sport.jim.bootstrap;

import ee.sport.jim.webapp.domain.ChampionshipType;
import ee.sport.jim.webapp.domain.Competition;
import ee.sport.jim.webapp.domain.CompetitionDistance;
import ee.sport.jim.webapp.domain.CompetitionPrice;
import ee.sport.jim.webapp.repository.ChampionshipTypeRepository;
import ee.sport.jim.webapp.repository.CompetitionRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CompetitionBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private final CompetitionRepository competitionRepository;
	private final ChampionshipTypeRepository championshipTypeRepository;

	public CompetitionBootstrap(CompetitionRepository competitionRepository,
															ChampionshipTypeRepository championshipTypeRepository) {
		this.competitionRepository = competitionRepository;
		this.championshipTypeRepository = championshipTypeRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<Competition> competitions = getCompetitions();
		competitionRepository.saveAll(competitions);
	}

	public List<Competition> getCompetitions() {
		List<Competition> competitions = new ArrayList<>();

		Optional<ChampionshipType> championshipTypeOptional = championshipTypeRepository.findByName("Raplamaa meistrivõistlused");
		if (!championshipTypeOptional.isPresent()) {
			throw new RuntimeException();
		}
		ChampionshipType championshipType = championshipTypeOptional.get();

		Competition competition = new Competition();
		competition.setName("Järvamaa Igamehemaraton");
		competition.setStartDate(getCalendarDate("25.08.2019"));
		competition.setEndDate(getCalendarDate("25.08.2019"));
		competition.setDescription("Jooksutrass Järvakandi alevis 4.2km ringil. Maraton 10 ringi. Sinu maraton 1, 2, 3, ... vöi 10 ringi");
		competition.setAddress("Järvakandi, Tallinna mnt 43");

		CompetitionDistance everyManDistance = new CompetitionDistance();
		everyManDistance.setName("Igamehejooks");
		everyManDistance.setLength(null);
		everyManDistance.setChampionshipType(null);
		everyManDistance.setSpecialNotes("Jookse palju jaksad");
		everyManDistance.setStartTime(getTimeDate("11:00"));
		everyManDistance.setStartNumbering(1);

		CompetitionDistance marathonDistance = new CompetitionDistance();
		marathonDistance.setName("Maraton");
		marathonDistance.setLength(BigDecimal.valueOf(42.2));
		marathonDistance.setChampionshipType(championshipType);
		marathonDistance.setSpecialNotes("Täispikk maraton");
		marathonDistance.setStartTime(getTimeDate("11:00"));
		marathonDistance.setStartNumbering(500);

		CompetitionPrice everyManPriceFirstPeriod = new CompetitionPrice();
		everyManPriceFirstPeriod.setPrice(BigDecimal.valueOf(10));
		everyManPriceFirstPeriod.setStartDate(getCalendarDate("25.10.2018"));
		everyManPriceFirstPeriod.setEndDate(getCalendarDate("25.01.2019"));
		everyManPriceFirstPeriod.setCompetitionDistance(everyManDistance);
		CompetitionPrice everyManPriceSecondPeriod = new CompetitionPrice();
		everyManPriceSecondPeriod.setPrice(BigDecimal.valueOf(15));
		everyManPriceSecondPeriod.setStartDate(getCalendarDate("26.01.2019"));
		everyManPriceSecondPeriod.setEndDate(getCalendarDate("25.08.2019"));
		everyManPriceSecondPeriod.setCompetitionDistance(everyManDistance);
		Set<CompetitionPrice> everyManPrices = Stream.of(everyManPriceFirstPeriod, everyManPriceSecondPeriod)
			.collect(Collectors.toSet());

		CompetitionPrice marathonPriceFirstPeriod = new CompetitionPrice();
		marathonPriceFirstPeriod.setPrice(BigDecimal.valueOf(20));
		marathonPriceFirstPeriod.setStartDate(getCalendarDate("25.10.2018"));
		marathonPriceFirstPeriod.setEndDate(getCalendarDate("25.01.2019"));
		marathonPriceFirstPeriod.setCompetitionDistance(marathonDistance);
		CompetitionPrice marathonPriceSecondPeriod = new CompetitionPrice();
		marathonPriceSecondPeriod.setPrice(BigDecimal.valueOf(25));
		marathonPriceSecondPeriod.setStartDate(getCalendarDate("26.01.2019"));
		marathonPriceSecondPeriod.setEndDate(getCalendarDate("25.08.2019"));
		marathonPriceSecondPeriod.setCompetitionDistance(marathonDistance);
		Set<CompetitionPrice> marathonPrices = Stream.of(marathonPriceFirstPeriod, marathonPriceSecondPeriod)
			.collect(Collectors.toSet());


		everyManDistance.getPrices().addAll(everyManPrices);
		everyManDistance.setCompetition(competition);

		marathonDistance.getPrices().addAll(marathonPrices);
		marathonDistance.setCompetition(competition);

		competition.getCompetitionDistances().add(everyManDistance);
		competition.getCompetitionDistances().add(marathonDistance);

		competitions.add(competition);
		return competitions;
	}

	private Date getCalendarDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	private Date getTimeDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("h:mm");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
