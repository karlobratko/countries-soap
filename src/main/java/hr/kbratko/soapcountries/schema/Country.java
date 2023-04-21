//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.05 at 06:43:23 PM CEST 
//


package hr.kbratko.soapcountries.schema;

import hr.kbratko.soapcountries.utils.Integers;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.w3c.dom.Node;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = Country.Constants.xmlTypeName,
  propOrder = {
    Country.Fields.name,
    Country.Fields.population,
    Country.Fields.capital,
    Country.Fields.currency
  }
)
@XmlRootElement(name = Country.Constants.xmlRootElementName)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Country {

  @XmlElement(required = true)
  protected String name;
  @XmlElement(required = true)
  protected Integer population;
  @XmlElement(required = true)
  protected String capital;
  @XmlElement(required = true)
  @XmlSchemaType(name = "string")
  protected Currency currency;

  public static Country from(Node node) {
    val nodes = node.getChildNodes();

    return Country.builder()
      .name(nodes.item(0).getFirstChild().getNodeValue())
      .population(Integers.parseIntOrElse(nodes.item(1).getFirstChild().getNodeValue(), null))
      .capital(nodes.item(2).getFirstChild().getNodeValue())
      .currency(Currency.valueOf(nodes.item(3).getFirstChild().getNodeValue()))
      .build();
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xmlTypeName = "country";

    public static final String xmlRootElementName = "country";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Fields {

    public static final String name = "name";

    public static final String population = "population";

    public static final String capital = "capital";

    public static final String currency = "currency";

  }

}