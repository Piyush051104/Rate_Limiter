public class RateLimiter {

    private int limit;
    private int count;
    private long startTime;

    public RateLimiter(int n) {
        limit = n;
        count = 0;
        startTime = System.nanoTime();
    }

    public synchronized boolean allowRequest() {

        long currentTime = System.nanoTime();

        if (currentTime - startTime >= 1_000_000_000L) {
            count = 0;
            startTime = currentTime;
        }

        if (count < limit) {
            count++;
            return true;
        }

        return false;
    }
}
