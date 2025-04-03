package concurrent;

import org.junit.jupiter.api.Test;

public class ThreadTest {
    @Test
    public void customThreadTest() {
        ExtendsThread t1 = new ExtendsThread();
        t1.start();

        ImplementRunnable ir = new ImplementRunnable();
        Thread t2 = new Thread(ir);
        t2.start();
    }

    class ExtendsThread extends Thread {
        public void run() {
            System.out.println("::: CustomThread");
        }
    }

    class ImplementRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("::: ImplementRunnable");
        }
    }

}
