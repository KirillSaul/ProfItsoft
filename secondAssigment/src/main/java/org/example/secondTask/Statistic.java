package org.example.secondTask;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;

public class Statistic extends SimpleFileVisitor<Path> {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    private final List<PoliceProtocol> policeProtocols;

    public Statistic() {
        policeProtocols = new ArrayList<>();
    }

    public void createStatistic(Path pathRead, Path pathWrite) {
        policeProtocols.clear();
        try {
            Files.walkFileTree(pathRead, Statistic.this);
            policeProtocols.sort(Comparator.comparingDouble(PoliceProtocol::getFineAmount));
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.writeValue(pathWrite.toFile(), Statistic.this);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void addToStatistic(PoliceProtocol policeProtocol) {
        if (policeProtocols.contains(policeProtocol)) {
            policeProtocols.get(policeProtocols.indexOf(policeProtocol)).addFineAmount(policeProtocol.getFineAmount());
        } else {
            policeProtocols.add(policeProtocol);
        }
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonParser jsonParser = jsonFactory.createParser(path.toFile())) {
            String type = null;
            Double fineAmount = null;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                if ("type".equals(jsonParser.getCurrentName())) {
                    jsonParser.nextToken();
                    type = jsonParser.getText();
                }
                if ("fine_amount".equals(jsonParser.getCurrentName())) {
                    jsonParser.nextToken();
                    fineAmount = jsonParser.getValueAsDouble();
                }
                if (nonNull(type) && nonNull(fineAmount)) {
                    addToStatistic(new PoliceProtocol(type, fineAmount));
                    type = null;
                    fineAmount = null;
                }

            }
        }
        return super.visitFile(path, attrs);
    }
}
