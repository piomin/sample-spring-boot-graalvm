package pl.piomin.samples.callme.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Callme {

    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;
    private String podName;
    private String version;

    public Callme() {
    }

    public Callme(Date addDate, String podName, String version) {
        this.addDate = addDate;
        this.podName = podName;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
