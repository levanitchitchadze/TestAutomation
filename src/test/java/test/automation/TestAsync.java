package test.automation;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestAsync {

    //    @Test
    void threadCreation() {
        Thread t1 = new Thread(this::printThreadName);
        Thread t2 = new Thread(this::printThreadName);
        Thread t3 = new Thread(this::printThreadName);
        Thread t4 = new Thread(this::printThreadName);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        printThreadName();

    }


    //    @Test
    void completableFutureCreation() {

        CompletableFuture.supplyAsync(() -> {
            try {
                printThreadName();
            } catch (Exception ignored) {
            }
            return "";
        });

        CompletableFuture.supplyAsync(() -> {
            try {
                printThreadName();
            } catch (Exception ignored) {
            }

            printThreadName();
            return "";
        });

        printThreadName();


    }


    void reactiveStreamsCreation() {

        List<Mono<Void>> monos = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
            monos.add(createMonos());

        Mono.when(monos).doOnTerminate(() -> {
            System.out.println("done");
        }).subscribe();
        printThreadName();

    }


    private Mono<Void> createMonos() {
        return Mono.fromRunnable(this::printThreadName).subscribeOn(Schedulers.boundedElastic()).then();
    }

    private void printThreadName() {
        String thread_name = Thread.currentThread().getName();
        int thread_length = thread_name.length();
        String thread_substring = thread_name.substring(thread_length - 1, thread_length);
        for (int i = 0; i < 10; i++) System.out.println("Thread name:" + thread_substring);
    }


}
