package org.example.firstTask;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public class MainFirstTask {
    public static void replaceToXml(String pathReadFile, String pathWriteFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathReadFile));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(pathWriteFile))
        ) {
            Pattern patternSurname = Pattern.compile("\\ssurname\\s?=\\s?\".+?\"");
            Pattern patternName = Pattern.compile("name\\s?=\\s?\".+?\"");
            Pattern patternEndPerson = Pattern.compile("/>");

            Matcher matcherSurname;
            Matcher matcherName;
            Matcher matcherEndPerson;

            String lineFromReader;
            String saveSurname = "";
            String concatLines = "";

            while (nonNull(lineFromReader = bufferedReader.readLine())) {
                matcherEndPerson = patternEndPerson.matcher(lineFromReader);
                concatLines = concatLines.concat(lineFromReader) + "\n";

                if (matcherEndPerson.find()) {
                    matcherSurname = patternSurname.matcher(concatLines);
                    if (matcherSurname.find()) {
                        saveSurname = matcherSurname.group().split("\"")[1];
                    }

                    matcherName = patternName.matcher(concatLines);
                    if (matcherName.find()) {
                        bufferedWriter.write(
                                concatLines
                                        .replaceAll(patternSurname.toString(), "")
                                        .replaceAll(patternName.toString(), matcherName.group()
                                                .replaceAll("\"$", " " + saveSurname.concat("\"")))
                        );
                    }
                    concatLines = "";
                }
            }
            bufferedWriter.write(concatLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        replaceToXml("files/firstTask/writeFile.xml", "files/firstTask/readFile.xml");
    }
}
