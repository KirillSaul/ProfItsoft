package org.example.firstTask;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainFirstTask {

    public static void main(String[] arg) throws InterruptedException {
        Statistic statistic = new Statistic();
        statistic.setPaths(Paths.get("files/firstTask/read"),
                Paths.get("files/firstTask/write/statistic.xml"));

        int threadQuantity = 8;
        long beginCountDownTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threadQuantity);

        for (int i = 0; i < threadQuantity; i++) {
            CompletableFuture.runAsync(statistic, executorService);
        }
        executorService.shutdown();

        if (executorService.awaitTermination(7, TimeUnit.HOURS)) {
            statistic.createStatistic();
            System.out.println(System.currentTimeMillis() - beginCountDownTime);
        }
    }
}
