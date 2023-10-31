package background;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BackgroundTest {

    @Test
    public void backgroundTest() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        BackgroundService.with(executorService)
                .forFirst("Hello")
                .execute(c -> System.out.println(c));
    }

}
