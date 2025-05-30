package shared.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Класс, представляющий фильм в коллекции.
 * Сортировка по умолчанию - по названию (name).
 */
public class Movie implements Comparable<Movie>, Serializable {
    private long id; // > 0, уникальное, генерируется автоматически
    private String name; // не null, не пустое
    private Coordinates coordinates; // не null
    private ZonedDateTime creationDate; // не null, генерируется автоматически
    private Integer oscarsCount; // > 0, может быть null
    private int length; // > 0
    private MovieGenre genre; // может быть null
    private MpaaRating mpaaRating; // может быть null
    private Person director; // не null
    private String ownerLogin; // логин владельца (добавлено для LAB7)

    // Конструкторы
    public Movie() {
        this.creationDate = ZonedDateTime.now();
    }

    public Movie(String name, Coordinates coordinates, Integer oscarsCount,
                 int length, MovieGenre genre, MpaaRating mpaaRating, Person director) {
        this();
        setName(name);
        setCoordinates(coordinates);
        setOscarsCount(oscarsCount);
        setLength(length);
        setGenre(genre);
        setMpaaRating(mpaaRating);
        setDirector(director);
    }

    // Геттеры и сеттеры с валидацией
    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    // Аналогичные валидации для других полей...
    // Полный код всех геттеров/сеттеров приведён ниже

    // Методы для сравнения
    @Override
    public int compareTo(Movie other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", length=" + length +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", director=" + director +
                ", ownerLogin='" + ownerLogin + '\'' +
                '}';
    }

    // Полные геттеры/сеттеры для всех полей
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("Creation date cannot be null");
        }
        this.creationDate = creationDate;
    }

    public Integer getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Integer oscarsCount) {
        if (oscarsCount != null && oscarsCount <= 0) {
            throw new IllegalArgumentException("Oscars count must be greater than 0");
        }
        this.oscarsCount = oscarsCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        this.length = length;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        if (director == null) {
            throw new IllegalArgumentException("Director cannot be null");
        }
        this.director = director;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
}