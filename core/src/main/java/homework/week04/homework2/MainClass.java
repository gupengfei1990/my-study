package homework.week04.homework2;

import javax.xml.ws.Holder;
import java.util.concurrent.*;

public class MainClass {

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //===========方式1 start===========

        Holder<String> result1 = new Holder<>();
        Thread t1 = new Thread(() -> result1.value = myFunction());
        t1.start();
        t1.join();
        System.out.println("方式1拿到方法返回值：" + result1.value);
        //============方式1 end============


        //===========方式2 start===========
        Holder<String> result2 = new Holder<>();
        Runnable runnable2 = () -> result2.value = myFunction();
        Thread t2 = new Thread(runnable2);
        t2.start();
        t2.join();
        System.out.println("方式2拿到方法返回值：" + result2.value);
        //============方式2 end============


        //===========方式3 start===========
        FutureTask<String> task3 = new FutureTask<>(() -> myFunction());
        new Thread(task3).start();
        String result3 = task3.get();
        System.out.println("方式3拿到方法返回值：" + result3);
        //============方式3 end============


        //===========方式4 start===========
        Holder<String> result4 = new Holder<>();
        Runnable runnable4 = () -> result4.value = myFunction();
        Future future4 = threadPool.submit(runnable4);
        future4.get();
        System.out.println("方式4拿到方法返回值：" + result4.value);
        //============方式4 end============


        //===========方式5 start===========
        Holder<String> result5 = new Holder<>();
        Future<Holder<String>> future5 = threadPool.submit(() -> result5.value = myFunction(), result5);
        System.out.println("方式5拿到方法返回值：" + future5.get().value);
        //============方式5 end============


        //===========方式6 start===========
        FutureTask<String> task6 = new FutureTask<>(() -> myFunction());
        threadPool.submit(task6);
        String result6 = task6.get();
        System.out.println("方式6拿到方法返回值：" + result6);
        //============方式6 end============


        //===========方式7 start===========
        Callable<String> task7 = () -> myFunction();
        Future<String> future7 = threadPool.submit(task7);
        String result7 = future7.get();
        System.out.println("方式7拿到方法返回值：" + result7);
        //============方式7 end============


        //===========方式8 start===========
        String result8 = CompletableFuture.supplyAsync(() -> myFunction()).get();
        System.out.println("方式8拿到方法返回值：" + result8);
        //============方式8 end============


        //===========方式9 start===========
        Holder<String> result9 = new Holder<>();
        Runnable runnable9 = () -> result9.value = myFunction();
        threadPool.submit(runnable9);
        threadPool.shutdown();
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("方式9拿到方法返回值：" + result9.value);
        //============方式9 end============

    }


    private static String myFunction() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "这是一个方法返回值";
    }
}
