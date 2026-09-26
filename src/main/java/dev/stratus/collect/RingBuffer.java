package dev.stratus.collect;

import java.util.Arrays;

public final class RingBuffer<T> {

    private final Object[] data;
    private int head;
    private int size;

    public RingBuffer(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be positive");
        this.data = new Object[capacity];
    }

    public void add(T value) {
        data[(head + size) % data.length] = value;
        if (size < data.length) {
            size++;
        } else {
            head = (head + 1) % data.length;
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(index);
        return (T) data[(head + index) % data.length];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public void clear() {
        Arrays.fill(data, null);
        head = 0;
        size = 0;
    }
}
