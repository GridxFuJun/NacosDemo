package com.gridx.nacos.client.convert;

public interface ConfigConverter<T> {

    /**
     * 自定义类型转换器，将string转换成对应的类型
     * @param content
     * @param t
     * @return
     */
    public T convert(String content,Class<T> t);

}
