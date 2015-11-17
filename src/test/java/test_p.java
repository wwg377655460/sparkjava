import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import util.HttpRequest;

/**
 * Created by wsdevotion on 15/10/18.
 */
public class test_p {

    @Test
    public void test(){
        JSONObject json = new JSONObject();
        json.put("username","wer");
        json.put("password","123");

        String url = "http://localhost:8080/testp";

        HttpRequest.sendPost(url, json.toString());
    }
}
