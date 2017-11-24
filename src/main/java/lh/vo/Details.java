package lh.vo;

import java.io.Serializable;

public class Details implements Serializable {
    private Integer did, pid, stid, tid, rid, eid, amount, rflag;
    private String title, photo;
    private Double price;
    private Res res;
    private Type type;
    private Subtype subtype;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Subtype getSubtype() {
        return subtype;
    }

    public void setSubtype(Subtype subtype) {
        this.subtype = subtype;
    }

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }


    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Details{" +
                "did=" + did +
                ", pid=" + pid +
                ", stid=" + stid +
                ", tid=" + tid +
                ", rid=" + rid +
                ", eid=" + eid +
                ", amount=" + amount +
                ", rflag=" + rflag +
                ", title='" + title + '\'' +
                ", photo='" + photo + '\'' +
                ", price=" + price +
                ", res=" + res +
                ", type=" + type +
                ", subtype=" + subtype +
                '}';
    }
}
