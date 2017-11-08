package lh.vo;

import java.io.Serializable;

public class Dept implements Serializable {
    private String title;
    private Integer did, sflag;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getSflag() {
        return sflag;
    }

    public void setSflag(Integer sflag) {
        this.sflag = sflag;
    }
}
