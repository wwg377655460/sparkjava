package dao;


import entity.Bill;
import util.DbFactory;

/**
 * Created by wsdevotion on 15/10/14.
 */
public class BillDao extends DbFactory<Bill> {



    @Override
    public Bill get(int id) {
        return getDao().fetch(Bill.class, id);
    }

    //加入账单
    public Bill insertBill(Bill bill){
        return getDao().insert(bill);
    }


}
