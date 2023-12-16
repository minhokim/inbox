package background;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class BackgroundService {
    private final ExecutorService executorService;

    public static BackgroundService with(ExecutorService executorService) {
        return new BackgroundService(executorService);
    }

    public interface Runner {
        void execute(Consumer<String> consumer);
    }

    public Runner forFirst(String str) {
        return new BackgroundSingleRunner(str);
    }

    @RequiredArgsConstructor
    public class BackgroundSingleRunner implements Runner {
        private final String str;
        @Override
        public void execute(Consumer<String> consumer) {
            executorService.execute(() -> consumer.accept(str));
        }
    }
}
