package org.example.secondTask;

import java.nio.file.Paths;

public class MainSecondTask {

    public static void main(String[] arg) {
        Statistic statistic = new Statistic();
        statistic.createStatistic(Paths.get("files/secondTask/read"),
                Paths.get("files/secondTask/write/statistic.xml"));
    }
}
