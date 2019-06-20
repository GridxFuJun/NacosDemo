package com.gridx.nacos.client.entity;

import java.io.Serializable;
import java.util.List;

public class JsonConfig implements Serializable {

    private String hello;

    private List<String> list;

    public JsonConfig() {
    }

    public JsonConfig(String hello, List<String> list) {
        this.hello = hello;
        this.list = list;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GridxConfig{" +
                "hello='" + hello + '\'' +
                ", list=" + list +
                '}';
    }
}
