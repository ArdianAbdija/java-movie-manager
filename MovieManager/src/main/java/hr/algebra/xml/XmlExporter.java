package hr.algebra.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlExporter {

    private XmlExporter() {
    }

    public static void exportMovie(MovieXml movie, File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(MovieXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(movie, file);
    }
}
