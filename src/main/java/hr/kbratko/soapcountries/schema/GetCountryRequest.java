//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.05 at 06:43:23 PM CEST 
//


package hr.kbratko.soapcountries.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
  name = GetCountryRequest.Constants.xmlTypeName,
  propOrder = {
    GetCountryRequest.Fields.name
  }
)
@XmlRootElement(name = GetCountryRequest.Constants.xmlRootElementName)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetCountryRequest {

  @XmlElement(required = true)
  protected String name;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xmlTypeName = "";

    public static final String xmlRootElementName = "getCountryRequest";

    public static final String payloadRootLocalPart = "getCountryRequest";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Fields {

    public static final String name = "name";

  }

}
