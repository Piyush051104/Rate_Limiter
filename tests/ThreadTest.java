import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        int limit = 5;
        int threadCount = 20;

        RateLimiter limiter = new RateLimiter(limit);

        AtomicInteger success = new AtomicInteger(0);

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {

            threads[i] = new Thread(() -> {

                if (limiter.allowRequest()) {
                    success.incrementAndGet();
                }

            });

            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        if (success.get() != limit) {
            throw new RuntimeException(
                    "Test failed. Successful requests = "
                            + success.get()
            );
        }

        System.out.println("Thread test passed");
        System.out.println("Successful requests: " + success.get());
    }
}
