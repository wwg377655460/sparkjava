package dao;

import entity.Bill;
import entity.SetMes;
import org.nutz.dao.Cnd;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 15/11/14.
 */
public class SetMesDao extends DbFactory<SetMes> {

    @Override
    public SetMes get(int id) {
        return getDao().fetch(SetMes.class, id);
    }

    //获取默认信息
    public List<SetMes> getSetMesByTime(int time){
        return getDao().query(SetMes.class, Cnd.where("time_e", "=", time));
    }

}
