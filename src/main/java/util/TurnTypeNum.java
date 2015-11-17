package util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.SetMes;
import entity.Time_e;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by wsdevotion on 15/10/15.
 */
public class TurnTypeNum {



    public static List<SetMes> turnSetMes(JSONObject json){
        JSONArray jsonArray = json.getJSONArray("data");
        List<SetMes> list = new ArrayList<>();
        SetMes setMes = null;
        for(int i=0; i<jsonArray.size(); i++){
            setMes = new SetMes();
            JSONObject jsonObject = JSON.parseObject(jsonArray.get(i).toString());
            setMes.setType_id(jsonObject.getInteger("type_id"));
            setMes.setChoose(jsonObject.getInteger("choose"));
            setMes.setTime_e(jsonObject.getString("time"));
            list.add(setMes);
        }

        return list;
    }
}
