package com.gridx.nacos.client.convert;

import com.alibaba.fastjson.JSON;

public class JsonConfigConverter<T> implements ConfigConverter<T> {

    /**
     * json convert
     * @param config
     * @param t
     * @return
     */
    public T convert(String config,Class<T> t) {
        return JSON.parseObject(config, t);
    }

}
