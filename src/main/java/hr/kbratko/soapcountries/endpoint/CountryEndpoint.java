package hr.kbratko.soapcountries.endpoint;

import hr.kbratko.soapcountries.constants.SchemaConstants;
import hr.kbratko.soapcountries.repository.CountryRepository;
import hr.kbratko.soapcountries.schema.Countries;
import hr.kbratko.soapcountries.schema.Country;
import hr.kbratko.soapcountries.schema.GetCountryRequest;
import hr.kbratko.soapcountries.schema.GetCountryResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.util.ResourceUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class CountryEndpoint {

  private final CountryRepository countryRepository;

  @PayloadRoot(namespace = SchemaConstants.namespaceUri, localPart = GetCountryRequest.Constants.payloadRootLocalPart)
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    val countries = Countries.from(countryRepository.findAll());

    try {
      // create file
      val file = new File(Constants.xmlFilterFileName);

      // write Countries to file with JAXB
      JAXBContext.newInstance(Countries.class)
        .createMarshaller()
        .marshal(countries, file);

      // validate .xml file
      SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        .newSchema(new StreamSource(ResourceUtils.getFile(SchemaConstants.schemaPath)))
        .newValidator()
        .validate(new StreamSource(file));

      // read and parse .xml file to create Document
      val xmlDocument = DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()
        .parse(file);

      // filter Document for Country with XPath
      val node = (Node) XPathFactory.newInstance()
        .newXPath()
        .compile(Constants.xPathCountryByNameExpressionFormat.formatted(request.getName()))
        .evaluate(xmlDocument, XPathConstants.NODE);

      // delete file
      file.delete();

      return Objects.nonNull(node)
        ? GetCountryResponse.from(Country.from(node))
        : GetCountryResponse.empty();
    } catch (XPathExpressionException e) {
      log.error("Failed to evaluate XPath expression");
      throw new RuntimeException(e);
    } catch (SAXException e) {
      log.error("Failed to validate and parse .xml document");
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParserConfigurationException e) {
      log.error("Failed to create DocumentBuilder");
      throw new RuntimeException(e);
    } catch (JAXBException e) {
      log.error("Failed to write Countries to file");
      throw new RuntimeException(e);
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String xPathCountryByNameExpressionFormat = "/countries/country[name='%s']";

    private static final String xmlFilterFileName = "countries.xml";

  }

}
