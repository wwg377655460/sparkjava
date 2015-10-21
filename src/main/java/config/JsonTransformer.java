package config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import spark.ResponseTransformer;

/**
 * Created by wsdevotion on 15/10/18.
 */
public class JsonTransformer implements ResponseTransformer {

//    private Gson gson = new Gson();

    private JSONObject json = new JSONObject();

    @Override
    public String render(Object model) {
        return JSON.toJSONString(model);
    }
}
