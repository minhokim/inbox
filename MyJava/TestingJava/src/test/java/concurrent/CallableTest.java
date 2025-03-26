package concurrent;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        MyCallable myCallableTask1 = new MyCallable("1");
        MyCallable myCallableTask2 = new MyCallable("2");

        Future<String> future1 = executorService.submit(myCallableTask1);
        Future<String> future2 = executorService.submit(myCallableTask2);
        System.out.println(future1.get());
        System.out.println(future2.get());

        executorService.shutdown();
    }


}

class MyCallable implements Callable<String> {
    private String taskName;

    public MyCallable(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "CallableTest " + taskName;
    }
}
