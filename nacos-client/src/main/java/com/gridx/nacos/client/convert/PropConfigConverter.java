package com.gridx.nacos.client.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class PropConfigConverter<T> implements ConfigConverter<T>{

    /**
     * properties convert
     * @param content
     * @param t
     * @return
     */
    public T convert(String content, Class<T> t) {

        String str = formatContent(content);
        return JSON.parseObject(str,t);

    }

    /**
     * content format
     * @param content
     * @return
     */
    private String formatContent(String content) {
        JSONObject object = new JSONObject();
        String[] strs = content.split("\n");
        for(String str : strs){
            String[] kv = str.split("=",2);
            object.put(kv[0],kv[1]);
        }
        return object.toJSONString();
    }

}
