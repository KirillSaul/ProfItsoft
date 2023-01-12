package org.example.firstTask;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainFirstTask {

    public static void main(String[] arg) {
        int threadQuantity = 8;
        long beginCountDownTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(threadQuantity);
        Statistic.setPaths(Paths.get("files/firstTask/read"), Paths.get("files/firstTask/write/statistic.xml"));

        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        for (int i = 0; i < threadQuantity; i++) {
            completableFutures.add(CompletableFuture.runAsync(new Statistic(), executorService));
        }
        executorService.shutdown();

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
        Statistic.createStatistic();
        System.out.println(System.currentTimeMillis() - beginCountDownTime);
    }
}
