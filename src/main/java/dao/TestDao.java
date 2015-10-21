package dao;

import java.util.Date;

/**
 * Created by wsdevotion on 15/10/18.
 */
public class TestDao {

    public String testDao(String user){
        String u = user + new Date();
        return u;
    }
}
