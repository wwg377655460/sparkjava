package entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by wsdevotion on 15/11/14.
 */
@Table("user_act_m")
public class SetMes {

    @Id
    private int id;
    @Column
    private int type_id;
    @Column
    private String time_e;
    @Column
    private int choose;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }


    public String getTime_e() {
        return time_e;
    }

    public void setTime_e(String time_e) {
        this.time_e = time_e;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }
}

