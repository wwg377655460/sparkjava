import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import util.HttpRequest;
import util.TimeUtil;

/**
 * Created by wsdevotion on 15/10/15.
 */
public class UserTest {


    @Test
    public void insertUser(){
        JSONObject json = new JSONObject();
        json.put("username","wer123");
        json.put("password","123456");
        json.put("password_sec","123");
        String url = "http://localhost:8080/insert";

        String param = json.toString();
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }

    @Test
    public void turnTimeType(){
        TimeUtil.turnTimeType(System.currentTimeMillis() + "");
    }

    @Test
    public void login(){
        JSONObject json = new JSONObject();
        json.put("username","wer1234");
        json.put("password","123456");
        String url = "http://localhost:8080/login";

        String param = json.toString();
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }

    @Test
    public void getUserMessage(){
        JSONObject json = new JSONObject();
        json.put("username","wer1234");
        String url = "http://localhost:8080/getUserMessage/67568579247FBFBB9C1B093D008F8608";

        String param = json.toString();
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }
    //67568579247FBFBB9C1B093D008F8608
    @Test
    public void getBillsMes(){
        JSONObject json = new JSONObject();
        json.put("username","wer1234");
        String url = "http://localhost:8080/getBillsMessage/67568579247FBFBB9C1B093D008F8608";

        String param = json.toString();
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }

    @Test
    public void getMaxType(){


//        String url = "http://localhost:8080/getMemoMes";
//        String s = HttpRequestJ.post(url, "");
//        System.out.println(s);

        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(int i=1; i<=20; i++){
            if(i==3){
                json.put("choose", 1000);
            }else{
                json.put("choose", i);
            }
            json.put("type_id", i);
            json.put("time",System.currentTimeMillis());
            jsonArray.add(json.toString());
            json.clear();
        }
        JSONObject json1 = new JSONObject();
        json1.put("data", jsonArray);

        String url = "http://localhost:8080/getMaxType";
        String param = json1.toString();
        System.out.println(param);
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }

    @Test
    public void updateImgurl(){
        JSONObject json = new JSONObject();
        json.put("imgurl", "www.123.com");

        String url = "http://localhost:8080/updateimage/wer12";
        String param = json.toString();
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }



    @Test
    public void addType(){
        JSONObject json = new JSONObject();
        json.put("name", "娱乐");

        String url = "http://localhost:8080/addType/wer1";
        String param = json.toString();
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }

    @Test
    public void updateUserMessage(){
        JSONObject json = new JSONObject();
        json.put("username","wer1234");
        JSONArray arr = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("type_id",7);
        json1.put("money",200.0);
        json1.put("remark","123");
        json1.put("update_time",System.currentTimeMillis());
        arr.add(json1);
        JSONObject json2 = new JSONObject();
        json2.put("type_id", 3);
        json2.put("money", 300.0);
        json2.put("remark","123");
        json2.put("update_time",System.currentTimeMillis());
        arr.add(json2);
        json.put("bills", arr);
        String url = "http://localhost:8080/updateUserMessage/67568579247FBFBB9C1B093D008F8608";

        String param = json.toString();

//        System.out.println(param);
        String s = HttpRequest.sendPost(url, param);
        System.out.println(s);
    }
}
