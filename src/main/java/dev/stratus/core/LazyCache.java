package dev.stratus.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class LazyCache<K, V> {

    private final Map<K, V> map = new ConcurrentHashMap<>();
    private final long ttlMillis;
    private final Map<K, Long> timestamps = new ConcurrentHashMap<>();

    public LazyCache(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public V get(K key, Supplier<V> supplier) {
        Long ts = timestamps.get(key);
        long now = System.currentTimeMillis();
        if (ts == null || now - ts > ttlMillis || !map.containsKey(key)) {
            V value = supplier.get();
            map.put(key, value);
            timestamps.put(key, now);
            return value;
        }
        return map.get(key);
    }

    public void invalidate(K key) {
        map.remove(key);
        timestamps.remove(key);
    }

    public void clear() {
        map.clear();
        timestamps.clear();
    }
}
