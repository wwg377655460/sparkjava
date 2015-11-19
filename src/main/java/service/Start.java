package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import config.ConstMes;
import config.GetProMessage;
import entity.*;
import exception.AccessException;
import exception.AuthException;
import exception.InsertException;
import exception.NullRequestException;
import org.apache.log4j.PropertyConfigurator;
import redis.clients.jedis.Jedis;
import spark.servlet.SparkApplication;
import util.*;

import java.util.List;

import static spark.Spark.*;

/**
 * Created by wsdevotion on 15/11/16.
 */
public class Start extends BaseController implements SparkApplication {


    @Override
    public void init() {


        initMes();

        before("/*", ((request, response) -> response.type("application/json")));

        get("/hello", (req, res) -> "hello你好,SaveAPP");

        //登录
        post("/login", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request, 1);
            String username = HttpUtils.getStrOrDie(jsonObject, "username");
            String password = HttpUtils.getStrOrDie(jsonObject, "password");
            JSONObject jsonreturn = new JSONObject();
            //对密码进行加密处理
            String password_m = Md5.MD5_Secure(password);
            User user = getUserDao().loginUser(username, password_m);
            if (user == null) {
                throw new AuthException();
            } else {
                String access_token = "";
                //将token放入缓存
                Jedis jedis = null;
                try {
                    jedis = RedisFactory.get();
                    //username password 作为key
                    access_token = Md5.MD5_Secure(username + password_m);
                    if (!jedis.exists(Md5.MD5_Secure(username + password_m))) {
                        jedis.setex(Md5.MD5_Secure(username + password_m), 360000, username);
                    }
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
                jsonreturn.put("status", "1");
                jsonreturn.put("access_token", access_token);
                return jsonreturn.toString();
            }

        }));

        //上传图片地址
        put("/image/:username", ((request, response) -> {
            JSONObject jsonObject = HttpUtils.parseJson(request);
            String imgurl = HttpUtils.getStrOrDie(jsonObject, "imgurl");
            String username = request.params(":username");//获取用户名
            User user = getUserDao().getUserMes(username);
            if (!HttpUtils.isAccess(request, username)) {
                throw new AccessException();
            }
            if (user == null) {
                throw new AuthException();
            }
            user.setImgurl(imgurl);
            getUserDao().updateUser(user);
            return HttpUtils.returnMes("1");
        }));

        //获取用户账单信息
        get("/billsMessage/:username", ((request, response) -> {
            String username = request.params("username");
            if (!HttpUtils.isAccess(request, username)) {
                throw new AccessException();
            }
            List<Bill> userBill = getUserDao().getUserBill(username);
            User user = getUserDao().getUserMes(username);
            if (userBill == null || user == null) {
                throw new AuthException();
            }
            String bills = JSON.toJSONString(userBill);
            JSONArray bills_arr = (JSONArray) JSONArray.parse(bills);
            JSONObject json = new JSONObject();
            json.put("status", 1);
            json.put("bills", bills_arr);
            return json.toString();
        }));

        //获取用户信息
        get("/userMessage/:username", ((request, response) -> {
            String username = request.params(":username");
            if (!HttpUtils.isAccess(request, username)) {
                throw new AccessException();
            }
            User user = getUserDao().getUserMes(username);
            if (user == null) {
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
        put("/userMessage", ((request, response) -> {
            User user = JSON.parseObject(request.body().toString(), User.class);
            if (!HttpUtils.isAccess(request, user.getUsername())) {
                throw new AccessException();
            }
            User user_n = getUserDao().getUserMes(user.getUsername());
            if (user_n == null) {
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
        post("/user", (request, response) -> {
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
        post("/type/:username", ((request, response) -> {
            String username = request.params(":username");
            if (!HttpUtils.isAccess(request, username)) {
                throw new AccessException();
            }

            JSONObject jsonObject = HttpUtils.parseJson(request);
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
        post("/MaxType", "application/json", ((request, response) -> {
            JSONObject json = HttpUtils.parseJson(request);
            List<SetMes> list_p = TurnTypeNum.turnSetMes(json);
            int timeType = TimeUtil.turnTimeType(list_p.get(0).getTime_e());
            List<SetMes> list_a = getTimeDao().getMesByTime(timeType + "");
            List<SetMes> list_m = getSetmesDao().getSetMesByTime(timeType);
            int type = MathUtil.getMaxType(list_p, list_a, list_m);
            JSONObject jsonreturn = new JSONObject();
            jsonreturn.put("status", "1");
            jsonreturn.put("type", type);

            return jsonreturn.toString();
        }));

        exception(AuthException.class, ((e, request, response) -> {
            response.status(403);
            response.body(HttpUtils.returnMes("-1"));
        }));

        exception(AccessException.class, ((e, request, response) -> {
            response.status(405);
            response.body(HttpUtils.returnMes("-2"));
        }));

        exception(NullRequestException.class, ((e, request, response) -> {
            response.status(403);
            response.body(HttpUtils.returnMes("-2"));
        }));

        exception(InsertException.class, ((e, request, response) -> {
            response.status(401);
            response.body(HttpUtils.returnMes("0"));
        }));

        exception(JSONException.class, (e, request, response) -> {
            response.status(403);
            response.body(HttpUtils.returnMes("-1"));
        });

        exception(Exception.class, (e, request, response) -> {
            response.status(500);
            response.body(HttpUtils.returnMes("-1"));
        });
    }


    @Override
    public void destroy() {

    }

    private static void initMes() {
        try {
            //初始化日志
            String path = GetProMessage.class.getClassLoader().getResource("").toURI().getPath();
            PropertyConfigurator.configure(path + "log4j.properties");

            //初始化配置信息

            //Redis初始化
            RedisFactory.init(GetProMessage.getProMessage("host", ConstMes.REDISPROMES), Integer.parseInt(GetProMessage.getProMessage("port", ConstMes.REDISPROMES)));

            //清空redis
            Jedis jedis = null;
            try {
                jedis = RedisFactory.get();
                jedis.flushAll();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }
}
