package dao;

import entity.Bill;
import entity.Type;
import entity.User;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 15/11/14.
 */
public class TypeDao extends DbFactory<Type>{


    @Override
    public Type get(int id) {
        return getDao().fetch(Type.class, id);
    }

    //加入类型
    public Type insertType(Type type){
        return getDao().insert(type);
    }

    //获取所有类型
    public List<Type> getTypes(){
        return getDao().query(Type.class, null);
    }

    //获取用户信息
    public Type getTypeMes(String name) {
        return getDao().fetch(Type.class, name);
    }

}
