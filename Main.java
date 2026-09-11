import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n;
        int threadCount;

        System.out.print("Enter number of requests allowed per second: ");
        n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Limit should be greater than 0.");
            return;
        }

        System.out.print("Enter number of threads: ");
        threadCount = scanner.nextInt();

        if (threadCount <= 0) {
            System.out.println("Number of threads should be greater than 0.");
            return;
        }

        RateLimiter limiter = new RateLimiter(n);

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

            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted.");
            }
        }

        System.out.println("Successful requests: " + success.get());

        System.out.println(
                "Blocked requests: "
                        + (threadCount - success.get())
        );
    }
}
