package lh.vo;

import java.io.Serializable;
import java.util.List;

public class Groups implements Serializable {
    private String title,type;
    private Integer gid;
    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", gid=" + gid +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}
