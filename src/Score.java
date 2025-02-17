import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
    private String firstName;
    private int value;
    // Constructors
    public Score() {

    }
    public Score(String name, int score) {
        firstName = name;
        value = score;
    }
    public String toString() {
        return firstName + ": " + value;
    }
    public int getValue() {
        return value;
    }
    @Override
    public int compareTo(Score other) {
        return Integer.compare(other.value, this.value);
    }
}
