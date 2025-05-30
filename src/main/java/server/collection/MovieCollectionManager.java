package server.collection;

import shared.models.Movie;
import java.util.*;
import java.util.concurrent.locks.*;

public class MovieCollectionManager {
    private static MovieCollectionManager instance;
    private final PriorityQueue<Movie> collection;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Date initDate = new Date();

    private MovieCollectionManager() {
        this.collection = new PriorityQueue<>(Comparator.comparing(Movie::getName));
    }

    public static synchronized MovieCollectionManager getInstance() {
        if (instance == null) {
            instance = new MovieCollectionManager();
        }
        return instance;
    }

    public void add(Movie movie) {
        lock.writeLock().lock();
        try {
            collection.add(movie);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return collection.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    // ... другие методы управления коллекцией
}