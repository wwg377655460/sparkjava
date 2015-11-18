package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import entity.*;
import exception.AuthException;
import exception.InsertException;
import exception.NullRequestException;
import javafx.application.Application;
import javafx.stage.Stage;
import spark.Route;
import spark.servlet.SparkApplication;
import spark.servlet.SparkFilter;
import util.*;

import java.util.List;

import static spark.Spark.*;

/**
 * Created by wsdevotion on 15/11/16.
 */
public class Start extends BaseController implements SparkApplication {


    @Override
    public void init() {

        before("/*", ((request, response) -> response.type("application/json")));

        get("/hello", (req, res) -> "hello你好,SaveAPP");

        //登录
        post("/login", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request.body());
            String username = HttpUtils.getStrOrDie(jsonObject, "username");
            String password = HttpUtils.getStrOrDie(jsonObject, "password");
            JSONObject jsonreturn = new JSONObject();
            //对密码进行加密处理
            String password_m = Md5.MD5_Secure(password);
            User user = getUserDao().loginUser(username, password_m);
            if (user == null) {
                throw new AuthException();
            } else {
                String access_token = Md5.MD5_Secure(user.getPassword());
                jsonreturn.put("status","1");
                jsonreturn.put("access_token", access_token);
                return jsonreturn.toString();
            }

        }));

        //上传图片地址
        post("/updateimage/:username", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request.body());
            String imgurl = HttpUtils.getStrOrDie(jsonObject, "imgurl");
            String username = request.params(":username");//获取用户名
            User user = getUserDao().getUserMes(username);
            if (user == null) {
                throw new AuthException();
            }
            user.setImgurl(imgurl);
            getUserDao().updateUser(user);
            return HttpUtils.returnMes("1");
        }));

        //获取用户账单信息
        post("/getBillsMessage/:access_token", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request.body());
            String username = HttpUtils.getStrOrDie(jsonObject, "username");
            List<Bill> userBill = getUserDao().getUserBill(username);
            User user = getUserDao().getUserMes(username);
            String access_token = request.params(":access_token");//验证用户的正确性
            if (userBill == null || user == null) {
                throw new AuthException();
            }
            if(!Md5.MD5_Secure(user.getPassword()).equals(access_token)){
                throw new AuthException();
            }
            String bills = JSON.toJSONString(userBill);
            JSONArray bills_arr = (JSONArray) JSONArray.parse(bills);
            JSONObject json = new JSONObject();
            json.put("status", 1);
            json.put("bills", bills_arr);
            SparkFilter s = new SparkFilter();
            return json.toString();
        }));

        //获取用户信息
        post("/getUserMessage/:access_token", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request.body());
            String username = HttpUtils.getStrOrDie(jsonObject, "username");
            String access_token = request.params(":access_token");//验证用户的正确性
            User user = getUserDao().getUserMes(username);
            if(user == null){
                throw new AuthException();
            }
            if(!Md5.MD5_Secure(user.getPassword()).equals(access_token)){
                throw new AuthException();
            }
            if (user.getImgurl() == null) {
                user.setImgurl("");
            }
            user.setPassword("");
            String json = JSON.toJSONString(user);
            JSONObject jsonObject1 = JSON.parseObject(json);
            jsonObject1.put("status", "1");
            return jsonObject1.toString();
        }));


        //上传用户账户信息
        post("/updateUserMessage/:access_token", ((request, response) -> {
            User user = JSON.parseObject(request.body().toString(), User.class);
            String access_token = request.params("access_token");
            User user_n = getUserDao().getUserMes(user.getUsername());
            if(user_n == null){
                throw new AuthException();
            }
            if(!Md5.MD5_Secure(user_n.getPassword()).equals(access_token)){
                throw new AuthException();
            }
            getUserDao().updateUser(user_n);
            List<Bill> bills = user.getBills();
            for (Bill bill : bills) {
                //获取类型，更改用户统计表
                int type = bill.getType_id();
                String time = bill.getUpdate_time();
                //更新所有用户数据表
                int timetype = TimeUtil.turnTimeType(time);
                Time_e time_e = new Time_e();
                time_e.setType_id(type);
                time_e.setTime_e(timetype + "");
                time_e.setUser_id(user_n.getId());
                time_e.setUpdate_time(time);
                getTimeDao().insertTime_m(time_e);
                //更新账单信息
                bill.setUser_id(user_n.getId());
                bill.setUpdate_time(System.currentTimeMillis() + "");
                getBillDao().insertBill(bill);
            }
            return HttpUtils.returnMes("1");
        }));

        //注册
        post("/insert", (request, response) -> {
                    User user = HttpUtils.parseJsonObject(request.body().toString(), User.class);
                    user.setPassword(Md5.MD5_Secure(user.getPassword()));
                    User user1 = getUserDao().getUserMes(user.getUsername());
                    if (user1 == null) {
                        //注册
                        getUserDao().insertUser(user);
                        return HttpUtils.returnMes("1");
                    } else {
                        throw new InsertException();
                    }

                }
        );

        //添加类型
        post("/addType/:username", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request.body());
            String name = HttpUtils.getStrOrDie(jsonObject, "name");
            JSONObject jsonreturn = new JSONObject();
            //获取类型
            Type ty = getTypeDao().getTypeMes(name);
            //类型存在就返回
            if (ty != null) {
                jsonreturn.put("status", "1");
                jsonreturn.put("id", ty.getId());
                return jsonreturn.toString();
            }
            //获取用户
            String username = request.params(":username");
            User user = getUserDao().getUserMes(username);
            if (user == null) {
                throw new AuthException();
            }
            int user_id = user.getId();
            Type type = new Type();
            type.setName(name);
            type.setUser_id(user_id);
            jsonreturn.put("status", "1");
            jsonreturn.put("id", getTypeDao().insertType(type).getId());
            return jsonreturn.toString();


        }));

        //获取最可能的类型
        post("/getMaxType", "application/json", ((request, response) -> {
            JSONObject json = HttpUtils.parseJson(request.body());
            List<SetMes> list_p = TurnTypeNum.turnSetMes(json);
            int timeType = TimeUtil.turnTimeType(list_p.get(0).getTime_e());
            List<SetMes> list_a = getTimeDao().getMesByTime(timeType + "");
            List<SetMes> list_m = getSetmesDao().getSetMesByTime(timeType);
            int type = MathUtil.getMaxType(list_p, list_a, list_m);
            JSONObject jsonreturn = new JSONObject();
            jsonreturn.put("status","1");
            jsonreturn.put("type", type);

            return jsonreturn.toString();
        }));

        exception(AuthException.class, ((e, request, response) -> {
            response.body(HttpUtils.returnMes("-1"));
        }));

        exception(NullRequestException.class, ((e, request, response) -> {
            response.body(HttpUtils.returnMes("-2"));
        }));

        exception(InsertException.class, ((e, request, response) -> {
            response.body(HttpUtils.returnMes("0"));
        }));

        exception(JSONException.class, (e, request, response) -> {
            response.body(HttpUtils.returnMes("-1"));
        });

        exception(Exception.class, (e, request, response) -> {
            response.body(HttpUtils.returnMes("-1"));
        });
    }



    @Override
    public void destroy() {

    }
}
