package service;

import dao.*;
import entity.SetMes;

/**
 * Created by wsdevotion on 15/10/18.
 */
public class BaseController {

    private static BillDao billDao;
    private static TypeDao typeDao;
    private static TimeDao timeDao;
    private static UserDao userDao;
    private static SetMesDao setMesDao;

    public static BillDao getBillDao(){
        if(billDao==null){
            billDao = new BillDao();
            return billDao;
        }else{
            return billDao;
        }
    }

    public static TypeDao getTypeDao(){
        if(typeDao==null){
            typeDao = new TypeDao();
            return typeDao;
        }else{
            return typeDao;
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

    public static SetMesDao getSetmesDao(){
        if(setMesDao==null){
            setMesDao = new SetMesDao();
            return setMesDao;
        }else{
            return setMesDao;
        }
    }
}
