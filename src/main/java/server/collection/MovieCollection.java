package server.collection;

import shared.models.Movie;
import shared.models.MovieGenre;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Потокобезопасная коллекция для хранения объектов Movie.
 * Использует PriorityQueue для хранения и ReadWriteLock для синхронизации.
 */
public class MovieCollection {
    private final PriorityQueue<Movie> collection;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public MovieCollection() {
        this.collection = new PriorityQueue<>(Comparator.comparing(Movie::getName));
    }

    /**
     * Добавляет фильм в коллекцию.
     * @param movie Фильм для добавления.
     */
    public void addMovie(Movie movie) {
        lock.writeLock().lock();
        try {
            collection.add(movie);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Удаляет фильм по ID, если он принадлежит пользователю.
     * @param id ID фильма.
     * @param ownerLogin Логин владельца.
     * @return Удалённый фильм или null, если не найден или нет прав.
     */
    public Movie removeById(long id, String ownerLogin) {
        lock.writeLock().lock();
        try {
            Movie movie = collection.stream()
                    .filter(m -> m.getId() == id && m.getOwnerLogin().equals(ownerLogin))
                    .findFirst()
                    .orElse(null);
            if (movie != null) {
                collection.remove(movie);
            }
            return movie;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Обновляет фильм, если он принадлежит пользователю.
     * @param id ID фильма.
     * @param newMovie Новые данные.
     * @param ownerLogin Логин владельца.
     * @return true, если обновление успешно.
     */
    public boolean updateById(long id, Movie newMovie, String ownerLogin) {
        lock.writeLock().lock();
        try {
            Movie oldMovie = collection.stream()
                    .filter(m -> m.getId() == id && m.getOwnerLogin().equals(ownerLogin))
                    .findFirst()
                    .orElse(null);
            if (oldMovie != null) {
                collection.remove(oldMovie);
                newMovie.setId(id); // Сохраняем старый ID
                newMovie.setOwnerLogin(ownerLogin); // Сохраняем владельца
                collection.add(newMovie);
                return true;
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Возвращает первый элемент коллекции и удаляет его.
     * @return Первый фильм или null, если коллекция пуста.
     */
    public Movie removeHead() {
        lock.writeLock().lock();
        try {
            return collection.poll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Очищает коллекцию (только фильмы пользователя).
     * @param ownerLogin Логин владельца.
     */
    public void clear(String ownerLogin) {
        lock.writeLock().lock();
        try {
            collection.removeIf(movie -> movie.getOwnerLogin().equals(ownerLogin));
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Возвращает список всех фильмов.
     * @return Неизменяемый список фильмов.
     */
    public List<Movie> getAllMovies() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(collection);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Возвращает фильм с минимальными координатами.
     * @return Фильм или null, если коллекция пуста.
     */
    public Movie getMinByCoordinates() {
        lock.readLock().lock();
        try {
            return collection.stream()
                    .min(Comparator.comparingDouble((Movie) m ->
                                    Math.sqrt(m.getCoordinates().getX() + m.getCoordinates().getY()))
                            .orElse(null));

        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Возвращает фильм с максимальным ID.
     * @return Фильм или null, если коллекция пуста.
     */
    public Movie getMaxById() {
        lock.readLock().lock();
        try {
            return collection.stream()
                    .max(Comparator.comparingLong(Movie::getId))
                    .orElse(null);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Фильтрует фильмы по жанру и возвращает количество.
     * @param genre Жанр для сравнения.
     * @return Количество фильмов с жанром "выше" указанного.
     */
    public int countGreaterThanGenre(MovieGenre genre) {
        lock.readLock().lock();
        try {
            return (int) collection.stream()
                    .filter(m -> m.getGenre() != null && m.getGenre().compareTo(genre) > 0)
                    .count();
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Добавляет все фильмы из списка (используется при загрузке из БД).
     * @param movies Список фильмов.
     */
    public void addAll(List<Movie> movies) {
        lock.writeLock().lock();
        try {
            collection.addAll(movies);
        } finally {
            lock.writeLock().unlock();
        }
    }
}