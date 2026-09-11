public class ResetTest {

    public static void main(String[] args) throws InterruptedException {

        RateLimiter limiter = new RateLimiter(2);

        if (!limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        if (!limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        if (limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        Thread.sleep(1100);

        if (!limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        System.out.println("Reset test passed");
    }
}
