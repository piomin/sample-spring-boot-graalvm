package pl.piomin.samples.caller.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Caller {

    @Id
    @GeneratedValue
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addDate;
    private String podName;

    public Caller() {
    }

    public Caller(Date addDate, String podName) {
        this.addDate = addDate;
        this.podName = podName;
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
}
