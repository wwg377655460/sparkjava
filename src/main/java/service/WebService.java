package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import config.JsonTransformer;
import dao.TestDao;
import entity.Bill;
import entity.Time;
import entity.User;
import util.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

/**
 * Created by wsdevotion on 15/10/18.
 */
public class WebService extends BaseController {


    public static void main(String[] args) {
        TestDao testDao = new TestDao();

        port(8080);

        post("/hello", (req, res) -> "hello你好");

        //登录
        post("/login", ((request, response) -> {
            String jsons = request.body();
            JSONObject jsonObject = JSON.parseObject(jsons);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            JSONObject json = new JSONObject();
            String password_m = Md5.MD5(password + Data.Salt);
            User user = getUserDao().loginUser(username, password_m);
            if (user == null) {
                json.put("result", "0");
            } else {
                json.put("result", "1");
            }

            return json.toString();
        }));

        //获取用户账单信息
        post("/getBillsMessage", ((request, response) -> {
            String jsons = request.body();
            JSONObject jsonObject = JSON.parseObject(jsons);
            String username = jsonObject.getString("username");
            List<Bill> userBill = getUserDao().getUserBill(username);
            String bills = JSON.toJSONString(userBill);
            JSONArray bills_arr = (JSONArray) JSONArray.parse(bills);
            JSONObject json = new JSONObject();
            json.put("result", 1);
            json.put("bills", bills_arr);

            return json.toString();
        }));

        //获取用户信息
        post("/getUserMessage", ((request, response) -> {
            String jsons = request.body();
            JSONObject jsonObject = JSON.parseObject(jsons);
            String username = jsonObject.getString("username");
            User user = getUserDao().getUserMes(username);
            user.setPassword("");
            String json = JSON.toJSONString(user);
            return json;
        }));


        //上传用户账户信息
        post("/updateUserMessage", ((request, response) -> {
            String jsonObject = request.body();
            User user = JSON.parseObject(jsonObject.toString(), User.class);
            User user_n = getUserDao().getUserMes(user.getUsername());
            user_n.setGet_money_bef(user.getGet_money_bef());
            user_n.setPay_money_bef(user.getPay_money_bef());
            getUserDao().updateUser(user_n);
            JSONObject jsonObject1 = new JSONObject();
            List<Bill> bills = user.getBills();
            for (Bill bill : bills) {
                //获取类型，更改用户统计表
                int type = bill.getType();
                String time = bill.getUpdate_time();
                int timetype = TimeUtil.turnTimeType(time);
                Time times = getTimeDao().getTimeByType(timetype + "");
                Class<Time> cl = (Class<Time>) times.getClass();
                Method m1 = null;
                Method m2 = null;
                try {
                    m1 = cl.getMethod("getType_" + type);
                    int n = (Integer) m1.invoke(times);

                    m2 = cl.getMethod("setType_" + type, int.class);
                    m2.invoke(times, n + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                getTimeDao().updateTimeByType(times);
                bill.setUser_id(user_n.getId());
                bill.setUpdate_time(System.currentTimeMillis() + "");
                getBillDao().insertBill(bill);
            }
            jsonObject1.put("result", "1");
            return jsonObject1.toString();
        }));

        //注册
        post("/insert", (request, response) -> {
                    String jsonObject = request.body();
                    User user = JSON.parseObject(jsonObject.toString(), User.class);
                    user.setPassword(Md5.MD5(user.getPassword() + Data.Salt));
                    User user1 = getUserDao().getUserMes(user.getUsername());
                    JSONObject json = new JSONObject();
//            User user_new = getUserDao().insertUser(user);
                    if (user1 == null) {
                        //注册
                        getUserDao().insertUser(user);
                        json.put("result", "1");
                    } else {
                        json.put("result", "0");
                    }
//                    Set<String> headers = request.headers();
                    response.type("text/plain");
                    response.body(json.toString());
//                    response.header("Content-Type", "text/plain;charset=utf-8");
//                    response.header("contentType","application/json;charset=utf-8");
                    return json.toString();
                }
        );

        //获取最可能的类型
        post("/getMaxType", "application/json", ((request, response) -> {
            String jsonObject = request.body();
            Time time_a = JSON.parseObject(jsonObject.toString(), Time.class);
            int timeType = TimeUtil.turnTimeType(time_a.getTime());
            Time time_p = getTimeDao().getTimeByType(timeType + "");
            JSONObject json = new JSONObject();
            Map<Integer, Double> map_a = TurnTypeNum.turnType(time_a);
            Map<Integer, Double> map_p = TurnTypeNum.turnType(time_p);
//        Map<Integer, Double> map_f = new HashMap<Integer, Double>();
            Map<Integer, Double> map_f = MathUtil.getMap(timeType);
            int type = MathUtil.getMaxType(map_p, map_a, map_f);
            json.put("type", type);

            return json.toString();
        }));
    }


}
