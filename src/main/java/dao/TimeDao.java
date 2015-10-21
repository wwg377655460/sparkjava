package dao;


import entity.Time;
import util.DbFactory;

/**
 * Created by wsdevotion on 15/10/14.
 */
public class TimeDao extends DbFactory<Time> {

    @Override
    public Time get(int id) {
        return null;
    }

    //获取时间段类型数量
    public Time getTimeByType(String time){
        return getDao().fetch(Time.class, time);
    }

    //更改时间段类型数量
    public int updateTimeByType(Time time){
        return getDao().update(time);
    }
}
