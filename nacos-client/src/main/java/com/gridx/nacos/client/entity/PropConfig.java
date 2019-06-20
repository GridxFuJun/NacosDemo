package com.gridx.nacos.client.entity;

import java.io.Serializable;

public class PropConfig implements Serializable {

    private String name;
    private int age;
    private String city;
    private String sex;

    public PropConfig() {
    }

    public PropConfig(String name, int age, String city, String sex) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PropConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

}
