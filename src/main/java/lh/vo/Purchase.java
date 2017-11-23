package lh.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Purchase implements Serializable {
    private Integer pid, eid, meid, status;
    private String title;
    private Emp emp;
    private List<Details> allDetails;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public List<Details> getAllDetails() {
        return allDetails;
    }

    public void setAllDetails(List<Details> allDetails) {
        this.allDetails = allDetails;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getMeid() {
        return meid;
    }

    public void setMeid(Integer meid) {
        this.meid = meid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    private String note;
    private Double total;
    private Date pubdate;
}
