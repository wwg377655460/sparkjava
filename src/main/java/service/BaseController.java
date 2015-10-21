package service;

import dao.BillDao;
import dao.TimeDao;
import dao.UserDao;
import entity.Time;
import entity.User;

/**
 * Created by wsdevotion on 15/10/18.
 */
public class BaseController {

    private static BillDao billDao;
    private static TimeDao timeDao;
    private static UserDao userDao;

    public static BillDao getBillDao(){
        if(billDao==null){
            billDao = new BillDao();
            return billDao;
        }else{
            return billDao;
        }
    }

    public static TimeDao getTimeDao(){
        if(timeDao==null){
            timeDao = new TimeDao();
            return timeDao;
        }else{
            return timeDao;
        }
    }

    public static UserDao getUserDao(){
        if(userDao==null){
            userDao = new UserDao();
            return userDao;
        }else{
            return userDao;
        }
    }
}
