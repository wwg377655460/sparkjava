package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import exception.AuthException;
import exception.NullRequestException;
import redis.clients.jedis.Jedis;
import spark.Request;

import javax.xml.bind.ValidationException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Description:
 * Http Components 助手类
 * ======================
 * by WhiteBlue
 * on 15/10/15
 */
public class HttpUtils {


    public static String httpBuildQuery(Map<String, String> paramsMap) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("?");
            for (String key : paramsMap.keySet()) {
                stringBuilder.append(key).append("=").append(URLEncoder.encode(paramsMap.get(key), "UTF-8")).append("&");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getStrOrDie(JSONObject jsonObject, String key) throws ValidationException {
        if (jsonObject.containsKey(key)) {
            String value = jsonObject.getString(key);
            if (StrUtils.isBlank(value)) {
                throw new ValidationException("param " + key + " is empty");
            }
            return value;
        } else {
            throw new ValidationException("param " + key + " is null");
        }
    }

    public static String returnMes(String status){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        return jsonObject.toString();
    }



    public static int getIntOrDie(JSONObject jsonObject, String key) throws ValidationException {
        try {
            return Integer.valueOf(jsonObject.getString(key));
        } catch (Exception e) {
            throw new ValidationException("param " + key + " is empty");
        }
    }


    public static JSONObject parseJson(Request request) throws NullRequestException {
        String body = request.body();
        String access_token = request.headers("access_token");
        if (StrUtils.isBlank(body) || StrUtils.isBlank(access_token)) {
            throw new NullRequestException();
        }

        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

    public static JSONObject parseJson(Request request,int type) throws NullRequestException {
        String body = request.body();
        if(type != 1) {
            String access_token = request.headers("access_token");
            if (StrUtils.isBlank(body) || StrUtils.isBlank(access_token)) {
                throw new NullRequestException();
            }
        }else{
            if (StrUtils.isBlank(body)) {
                throw new NullRequestException();
            }
        }


        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject;
    }

    public static <T> T parseJsonObject(String request, Class<T> clz) throws NullRequestException, AuthException {
        String body = request.trim();
        if (StrUtils.isBlank(body)) {
            throw new NullRequestException();
        }
        T t = JSON.parseObject(body, clz);
        if(t == null){
            throw new AuthException();
        }
        return t;
    }

    public static Boolean isAccess(Request request, String username){
        Jedis jedis = null;
        String access_token = request.headers("access_token");
        try{
            jedis = RedisFactory.get();
            if(jedis.exists(access_token)){
                if(jedis.get(access_token).equals(username))
                    return true;
            }
            return false;
        }finally {
            if(jedis != null){
                jedis.close();

            }
        }
    }


}
