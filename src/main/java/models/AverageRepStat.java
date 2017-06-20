package models;

import javax.persistence.*;

/**
 * Created by PavelGrudina on 19.06.2017.
 */
@Entity
@Table(name = "STATISTIC")
public class AverageRepStat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    private int linesCount;
    private String longestWord;
    private String shortestWord;
    private int averageWordLength;

    public AverageRepStat() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public void setLinesCount(int linesCount) {
        this.linesCount = linesCount;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AverageRepStat that = (AverageRepStat) o;

        if (getId() != that.getId()) return false;
        if (getLinesCount() != that.getLinesCount()) return false;
        if (getAverageWordLength() != that.getAverageWordLength()) return false;
        if (getLongestWord() != null ? !getLongestWord().equals(that.getLongestWord()) : that.getLongestWord() != null)
            return false;
        return getShortestWord() != null ? getShortestWord().equals(that.getShortestWord()) : that.getShortestWord() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getLinesCount();
        result = 31 * result + (getLongestWord() != null ? getLongestWord().hashCode() : 0);
        result = 31 * result + (getShortestWord() != null ? getShortestWord().hashCode() : 0);
        result = 31 * result + getAverageWordLength();
        return result;
    }

    @Override
    public String toString() {
        return "AverageRepStat (" +
                "id=" + id +
                ", lines count=" + linesCount +
                ", longest word='" + longestWord + '\'' +
                ", shortest word='" + shortestWord + '\'' +
                ", average word length=" + averageWordLength +
                ')';
    }
}
