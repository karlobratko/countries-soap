package hr.kbratko.soapcountries.repository;

import hr.kbratko.soapcountries.schema.Country;
import hr.kbratko.soapcountries.schema.Currency;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CountryRepository {

  private static final List<Country> countries = new ArrayList<>();

  @PostConstruct
  public void initData() {
    Country spain = new Country();
    spain.setName("Spain");
    spain.setCapital("Madrid");
    spain.setCurrency(Currency.EUR);
    spain.setPopulation(46704314);

    Country poland = new Country();
    poland.setName("Poland");
    poland.setCapital("Warsaw");
    poland.setCurrency(Currency.PLN);
    poland.setPopulation(38186860);

    Country uk = new Country();
    uk.setName("United Kingdom");
    uk.setCapital("London");
    uk.setCurrency(Currency.GBP);
    uk.setPopulation(63705000);

    countries.addAll(
      List.of(spain, poland, uk)
    );
  }

  public List<Country> findAll() {
    return countries;
  }

  public Optional<Country> findByName(String name) {
    return countries.stream()
      .filter(country -> country.getName().equals(name))
      .findFirst();
  }

}
