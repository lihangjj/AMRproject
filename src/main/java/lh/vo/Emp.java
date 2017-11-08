package lh.vo;

import java.io.Serializable;

public class Emp implements Serializable{
   private String name,password,phone,sex,photo,note;
   private Integer eid,did,lid,heid,aflag;
   private Double salary;

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", photo='" + photo + '\'' +
                ", note='" + note + '\'' +
                ", eid=" + eid +
                ", did=" + did +
                ", lid=" + lid +
                ", heid=" + heid +
                ", aflag=" + aflag +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getHeid() {
        return heid;
    }

    public void setHeid(Integer heid) {
        this.heid = heid;
    }

    public Integer getAflag() {
        return aflag;
    }

    public void setAflag(Integer aflag) {
        this.aflag = aflag;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
