package entity;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * Created by wsdevotion on 15/10/14.
 */
@Table("users")
public class User {
    @Id
    private int id;
    @Name
    private String username;
    @Column
    private String password;
    @Column
    private String password_sec;
    @Column
    private String imgurl;


    @Many(target = Bill.class, field = "user_id")
    private List<Bill> bills;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_sec() {
        return password_sec;
    }

    public void setPassword_sec(String password_sec) {
        this.password_sec = password_sec;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
