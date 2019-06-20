package com.gridx.nacos.client.convert;

import org.yaml.snakeyaml.Yaml;

public class YamlConfigConverter<T> implements ConfigConverter<T>{

    /**
     * ymal convert
     * @param content
     * @param t
     * @return
     */
    public T convert(String content, Class<T> t) {

        return new Yaml().loadAs(content,t);

    }

}
