public class BasicTest {

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter(3);

        if (!limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        if (!limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        if (!limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        if (limiter.allowRequest()) {
            throw new RuntimeException("Test failed");
        }

        System.out.println("Basic test passed");
    }
}
