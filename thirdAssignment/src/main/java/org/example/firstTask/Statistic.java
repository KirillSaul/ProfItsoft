package org.example.firstTask;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

public class Statistic implements Runnable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    private final List<PoliceProtocol> policeProtocols;
    private Path pathWrite;
    private File[] files;
    private int needCheckFiles;

    public Statistic() {
        policeProtocols = Collections.synchronizedList(new ArrayList<>());
    }

    public void createStatistic() {
        try {
            policeProtocols.sort(Comparator.comparing(PoliceProtocol::getFineAmount));
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.writeValue(this.pathWrite.toFile(), Statistic.this);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } finally {
            policeProtocols.clear();
        }
    }

    public void setPaths(Path pathRead, Path pathWrite) {

        files = new File(pathRead.toString()).listFiles();
        if (isNull(files)) {
            throw new IllegalArgumentException("can`t find files: " + pathRead);
        }

        needCheckFiles = files.length;

        this.pathWrite = pathWrite;
    }

    private synchronized void addToStatistic(PoliceProtocol policeProtocol) {
        if (policeProtocols.contains(policeProtocol)) {
            policeProtocols.get(policeProtocols.indexOf(policeProtocol)).addFineAmount(policeProtocol.getFineAmount());
        } else {
            policeProtocols.add(policeProtocol);
        }
    }

    @Override
    public void run() {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            while (needCheckFiles > 0) {
                synchronized (this) {
                    needCheckFiles--;
                }
                try (JsonParser jsonParser = jsonFactory.createParser(files[needCheckFiles])) {
                    String type = null;
                    BigDecimal fineAmount = null;
                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        if ("type".equals(jsonParser.getCurrentName())) {
                            jsonParser.nextToken();
                            type = jsonParser.getText();
                        }
                        if ("fine_amount".equals(jsonParser.getCurrentName())) {
                            jsonParser.nextToken();
                            fineAmount = jsonParser.getDecimalValue();
                        }
                        if (nonNull(type) && nonNull(fineAmount)) {
                            addToStatistic(new PoliceProtocol(type, fineAmount));
                            type = null;
                            fineAmount = null;
                        }

                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
