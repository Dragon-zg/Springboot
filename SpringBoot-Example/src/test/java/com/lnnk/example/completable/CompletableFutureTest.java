package com.lnnk.example.completable;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lnnk
 * @date 2019/8/11 11:27
 **/
public class CompletableFutureTest {

    @Test
    public void thenApply() {
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " world").join();
        System.out.println(result);
    }

    @Test
    public void thenAccept() {
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s + " world"));
    }

    @Test
    public void thenRun() throws Exception {

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        })
                //对上一步的计算结果不关心，执行下一个操作`
                .thenRun(() -> System.out.println("thenRun hello world"));
        Thread.sleep(3000);
    }

    @Test
    public void thenCompose() {
        Supplier<String> taskA = () -> "hello";
        Function<String, CompletableFuture<String>> taskB = (t) -> CompletableFuture.supplyAsync(() -> t.toUpperCase());
        Consumer<String> taskC = (t) -> System.out.println("consumer: " + t);
        CompletableFuture.supplyAsync(taskA).thenCompose(taskB).thenAccept(taskC).join();
    }

    @Test
    public void thenCombine() {
        //结合两个CompletionStage的结果，进行转化后返回
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    @Test
    public void thenAcceptBoth() throws Exception {
        // 结合两个CompletionStage的结果，进行消耗
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));
        Thread.sleep(3000);
    }

    @Test
    public void runAfterBoth() throws Exception {
        //不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）。
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s1");
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s2");
            return "s2";
        }), () -> System.out.println("hello world"));
        Thread.sleep(3000);
    }

    @Test
    public void applyToEither() {
        //两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), s -> s).join();
        System.out.println(result);
    }

    @Test
    public void acceptEither() throws Exception {
        // 两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), System.out::println);
        Thread.sleep(4000);
    }

    @Test
    public void runAfterEither() throws Exception {
        // 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));
        Thread.sleep(1500);
    }

    @Test
    public void exceptionally() {
        //当运行时出现了异常，可以通过exceptionally进行补偿
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    @Test
    public void whenComplete() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    @Test
    public void handle() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //出现异常
            /*if (true) {
                throw new RuntimeException("测试一下异常情况");
            }*/
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }
}
