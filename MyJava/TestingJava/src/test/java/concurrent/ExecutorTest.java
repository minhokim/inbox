package concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ExecutorTest {

    @Test
    public void executor() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Executor"));
    }

    @Test
    public void executorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> "Hello World");

        try {
            String result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
