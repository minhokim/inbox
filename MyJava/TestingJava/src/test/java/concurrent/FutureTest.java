package concurrent;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.*;

public class FutureTest {

    @Test
    public void futureTest01() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> future = executor.submit( () -> {
            System.out.println(LocalTime.now() + " Starting runnable");
            Integer sum = 1 + 1;
            Thread.sleep(3000);
            return sum;
        });

        System.out.println(LocalTime.now() + " Waiting the task done");
        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(LocalTime.now() + " Result : " + result);
    }

    @Test
    public void futureTest02() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit( () -> {
            System.out.println(LocalTime.now() + " Doing something");
            Integer sum = 1 + 1;
            Thread.sleep(3000);
            future.complete(sum);
            return null;
        });

        System.out.println(LocalTime.now() + " Waiting the task done");
        Integer result = future.get();
        System.out.println(LocalTime.now() + " Result : " + result);
    }
}
