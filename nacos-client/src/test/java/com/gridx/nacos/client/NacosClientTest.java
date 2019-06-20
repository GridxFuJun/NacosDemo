package com.gridx.nacos.client;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.gridx.nacos.client.convert.JsonConfigConverter;
import com.gridx.nacos.client.convert.PropConfigConverter;
import com.gridx.nacos.client.convert.YamlConfigConverter;
import com.gridx.nacos.client.entity.JsonConfig;
import com.gridx.nacos.client.entity.PropConfig;
import com.gridx.nacos.client.utils.GridxNacosConfig;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class NacosClientTest {

    String serverAddr = "10.100.36.189";

    @Test
    public void testJson() throws NacosException {

        GridxNacosConfig config = new GridxNacosConfig(serverAddr);
        JsonConfig content = config.getConfig("Gridx_test", "DEFAULT_GROUP", 2000, JsonConfig.class,new JsonConfigConverter());
        System.out.println(content);

    }

    @Test
    public void testProp() throws NacosException {

        GridxNacosConfig config = new GridxNacosConfig(serverAddr);
        PropConfig content = config.getConfig("Gridx_prop", "DEFAULT_GROUP", 2000, PropConfig.class,new PropConfigConverter());
        //HashMap<String,Object> content = config.getConfig("Gridx_prop", "DEFAULT_GROUP", 2000, HashMap.class,new PropConfigConverter());
        System.out.println(content);

    }

    @Test
    public void testYaml() throws NacosException {

        GridxNacosConfig config = new GridxNacosConfig(serverAddr);
        HashMap<String,String> content = config.getConfig("Gridx_yaml", "DEFAULT_GROUP", 2000, HashMap.class,new YamlConfigConverter());
        System.out.println(content);

    }

    @Test
    public void testpublishConfig() throws NacosException {

        GridxNacosConfig config = new GridxNacosConfig(serverAddr);
        String dataId = "leotest";
        String content = "hello=world";
        boolean result = config.publishConfig(dataId, "DEFAULT_GROUP",content);
        assert result;

    }

    @Test
    public void testRemoveConfig() throws NacosException {

        GridxNacosConfig config = new GridxNacosConfig(serverAddr);
        String dataId = "leotest";
        boolean result = config.removeConfig(dataId, "DEFAULT_GROUP");
        assert result;

    }

    @Test
    public void testConfigListener() throws NacosException, InterruptedException {

        GridxNacosConfig config = new GridxNacosConfig(serverAddr);

        Listener listener = new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("update ：" + configInfo );
            }
        };

        config.addListener("leotest", "DEFAULT_GROUP", listener) ;

        int num = 0;
        while(true){

            num++;
            String content = config.getConfig("leotest", "DEFAULT_GROUP", 2000);
            System.out.println(num + " : " + content);
            Thread.sleep(1000 * 2);
            if(num == 20){
                config.removeListener("leotest","DEFAULT_GROUP",listener);
            }
        }


    }



}
