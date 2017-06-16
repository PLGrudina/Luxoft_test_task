package models;

import javax.persistence.*;

/**
 * Created by PavelGrudina on 16.06.2017.
 */
@Entity
@Table (name = "LINES")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column (length = 2000)
    private String fullLine;

    private String longestWord;
    private String shortestWord;
    private int averageWordLength;
    private int lineLength;

    public Line() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullLine() {
        return fullLine;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public int getLineLength() {
        return lineLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (getId() != line.getId()) return false;
        if (getLineLength() != line.getLineLength()) return false;
        return getFullLine() != null ? getFullLine().equals(line.getFullLine()) : line.getFullLine() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getFullLine() != null ? getFullLine().hashCode() : 0);
        result = 31 * result + getLineLength();
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", fullLine='" + fullLine + '\'' +
                ", longestWord='" + longestWord + '\'' +
                ", shortestWord='" + shortestWord + '\'' +
                ", averageWordLength=" + averageWordLength +
                ", lineLength=" + lineLength +
                '}';
    }
}
