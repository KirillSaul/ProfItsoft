package org.example.secondTask;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class PoliceProtocol extends SimpleFileVisitor<Path> {
    @JacksonXmlElementWrapper
    private Map<String, Double> statistic;

    public PoliceProtocol() {
        statistic = new LinkedHashMap<>();
    }

    public void createStatistic(Path pathRead, Path pathWrite, PoliceProtocol policeProtocol) {
        statistic.clear();
        try {
            Files.walkFileTree(pathRead, policeProtocol);

            statistic = statistic.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (x, y) -> y,
                                    LinkedHashMap::new)
                    );

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.writeValue(pathWrite.toFile(), policeProtocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeStatistic(String type, Double fineAmount) {
        if (statistic.containsKey(type)) {
            statistic.put(type, statistic.get(type) + fineAmount);
        } else {
            statistic.put(type, fineAmount);
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
                    makeStatistic(type, fineAmount);
                    type = null;
                    fineAmount = null;
                }

            }
        }
        return super.visitFile(path, attrs);
    }
}
