package dao;


import entity.SetMes;
import entity.Time_e;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import util.DbFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wsdevotion on 15/10/14.
 */
public class TimeDao extends DbFactory<Time_e> {

    @Override
    public Time_e get(int id) {
        return null;
    }

    //获取时间段类型所有用户数量
    public List<SetMes> getMesByTime(String time){
        Sql sql = Sqls.create("SELECT type_id, COUNT(*) AS choose FROM user_act_p WHERE time_e = "+ time +" ORDER BY type_id");
//        sql.params().set("name", time);
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Time_e> list = new LinkedList<Time_e>();
                Time_e time = new Time_e();
                while (rs.next()) {
                    time.setChoose(rs.getShort("choose"));
                    time.setType_id(rs.getInt("type_id"));
                    list.add(time);
                }
                return list;
            }
        });
        getDao().execute(sql);
        return sql.getList(SetMes.class);
    }

    //添加用户记录数量
    public Time_e insertTime_m(Time_e time){
        return getDao().insert(time);
    }

    //获取用户个人数据
//    public List<Time_e> getMesByTimeAndUser(String user_id, String time){
//        getDao().query(Time_e.class, Cnd.wrap())
//    }
}
