package lh.vo;

import java.io.Serializable;
import java.util.Date;

public class Take implements Serializable {
    private String note;
    private Integer tkid, geid, beid, rid, amount, status;
    private Date gdate, rdate;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getTkid() {
        return tkid;
    }

    public void setTkid(Integer tkid) {
        this.tkid = tkid;
    }

    public Integer getGeid() {
        return geid;
    }

    public void setGeid(Integer geid) {
        this.geid = geid;
    }

    public Integer getBeid() {
        return beid;
    }

    public void setBeid(Integer beid) {
        this.beid = beid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGdate() {
        return gdate;
    }

    public void setGdate(Date gdate) {
        this.gdate = gdate;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }
}
