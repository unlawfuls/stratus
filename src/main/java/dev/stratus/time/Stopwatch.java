package dev.stratus.time;

public final class Stopwatch {

    private long start;
    private long elapsed;
    private boolean running;

    public static Stopwatch createStarted() {
        Stopwatch sw = new Stopwatch();
        sw.start();
        return sw;
    }

    public void start() {
        if (!running) {
            start = System.nanoTime();
            running = true;
        }
    }

    public void stop() {
        if (running) {
            elapsed += System.nanoTime() - start;
            running = false;
        }
    }

    public void reset() {
        elapsed = 0;
        running = false;
    }

    public long elapsedMillis() {
        long total = elapsed;
        if (running) total += System.nanoTime() - start;
        return total / 1_000_000;
    }

    public boolean isRunning() {
        return running;
    }
}
