package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PavelGrudina on 16.06.2017.
 */
@Entity
@Table (name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    private String name;

    private String url;

    @OneToOne
    private AverageRepStat reportStatistics;

    @OneToMany (cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List <Line> allLines = new ArrayList<>();

    public Report() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Line> getAllLines() {
        return allLines;
    }

    public void setAllLines(List<Line> allLines) {
        this.allLines = allLines;
    }

    public AverageRepStat getReportStatistics() {
        return reportStatistics;
    }

    public void setReportStatistics(AverageRepStat reportStatistics) {
        this.reportStatistics = reportStatistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (getId() != report.getId()) return false;
        if (getName() != null ? !getName().equals(report.getName()) : report.getName() != null) return false;
        if (getUrl() != null ? !getUrl().equals(report.getUrl()) : report.getUrl() != null) return false;
        return getAllLines() != null ? getAllLines().equals(report.getAllLines()) : report.getAllLines() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getAllLines() != null ? getAllLines().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
