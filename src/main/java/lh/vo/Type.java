package lh.vo;

import java.io.Serializable;

public class Type implements Serializable {
    private Integer tid;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTid() {

        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
