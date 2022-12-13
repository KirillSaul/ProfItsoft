package org.example.firstTask;

import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainFirstTask {

    public static void main(String[] arg) throws InterruptedException {
        int threadQuantity = 8;
        long beginCountDownTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threadQuantity);
        Statistic.setPaths(Paths.get("files/firstTask/read"), Paths.get("files/firstTask/write/statistic.xml"));

        for (int i = 0; i < threadQuantity; i++) {
            CompletableFuture.runAsync(new Statistic(), executorService);
        }
        executorService.shutdown();
        if (executorService.awaitTermination(7, TimeUnit.HOURS)) {
            Statistic.createStatistic();
            System.out.println(System.currentTimeMillis() - beginCountDownTime);
        }
    }
}
