package com.gridx.nacos.client.utils;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.gridx.nacos.client.convert.ConfigConverter;

import java.io.Serializable;
import java.util.Properties;

public class GridxNacosConfig implements Serializable {

    private Properties properties = new Properties();
    private ConfigService  configService = null;

    /**
     * use customer namespace，encrypt with md5
     * @param serverAddr
     * @param namespace
     */
    public GridxNacosConfig(String serverAddr, String namespace) {

        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, namespace);
        initConf();

    }

    /**
     * use default namespace（public）
     * @param serverAddr
     */
    public GridxNacosConfig(String serverAddr) {

        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        initConf();

    }

    private void initConf() {

        try {
            configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get config
     *
     * @param dataId
     * @param group
     * @param timeoutMs
     * @return
     * @throws NacosException
     */
    public String getConfig(String dataId, String group, long timeoutMs) throws NacosException {

        return configService.getConfig(dataId,group,timeoutMs);

    }

    /**
     * Get config and convert
     *
     * @param dataId
     * @param group
     * @param timeoutMs
     * @param clazz
     * @param <T>
     * @return
     * @throws NacosException
     */
    public <T> T getConfig(String dataId, String group, long timeoutMs, Class<T> clazz, ConfigConverter<T> converter) throws NacosException {

        String content = configService.getConfig(dataId,group,timeoutMs);
        return converter.convert(content,clazz);

    }



    /**
     * Add a listener to the configuration, after the server modified the
     * configuration, the client will use the incoming listener callback.
     * Recommended asynchronous processing, the application can implement the
     * getExecutor method in the ManagerListener, provide a thread pool of
     * execution. If provided, use the main thread callback, May block other
     * configurations or be blocked by other configurations.
     *
     * @param dataId   dataId
     * @param group    group
     * @param listener listener
     * @throws NacosException NacosException
     */
    public void addListener(String dataId, String group, Listener listener) throws NacosException{

        configService.addListener(dataId,group,listener);

    };

    /**
     * Publish config.
     *
     * @param dataId  dataId
     * @param group   group
     * @param content content
     * @return Whether publish
     * @throws NacosException NacosException
     */
    public boolean publishConfig(String dataId, String group, String content) throws NacosException{

        return configService.publishConfig(dataId,group,content);

    };

    /**
     * Remove config
     *
     * @param dataId dataId
     * @param group  group
     * @return whether remove
     * @throws NacosException NacosException
     */
    public boolean removeConfig(String dataId, String group) throws NacosException{

        return configService.removeConfig(dataId,group);

    };

    /**
     * Remove listener
     *
     * @param dataId   dataId
     * @param group    group
     * @param listener listener
     */
    public void removeListener(String dataId, String group, Listener listener){

        configService.removeListener(dataId,group,listener);

    };

    /**
     * Get server status
     *
     * @return whether health
     */
    public String getServerStatus(){

        return configService.getServerStatus();

    };


}
