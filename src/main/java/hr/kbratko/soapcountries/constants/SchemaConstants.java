package hr.kbratko.soapcountries.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchemaConstants {

  public static final String namespaceUri = "http://www.kbratko.hr/soapcountries/schema";

  public static final String schemaPath = "classpath:schema.xsd";

}
