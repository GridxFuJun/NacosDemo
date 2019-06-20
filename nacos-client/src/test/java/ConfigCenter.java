import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.gridx.nacos.client.convert.JsonConfigConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.Executor;

public class ConfigCenter {

    //nacos服务地址
    private static final String serverAddr = "10.100.36.189:8848";
    //所属分组
    private static final String group = "DEFAULT_GROUP";
    //命名空间
    private static final String namespace = "6f1524c9-aaf1-4d7c-94ae-1086d06346a9";


    Properties properties = new Properties();

    @Before
    public void  before(){

        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        //使用默认namespace（public）不需要指定，其他namespace必须要指定
        //properties.put(PropertyKeyConst.NAMESPACE, namespace);

    }

    /**
     * 测试配置项动态生效
     */
    @Test
    public void testDynamicConfig(){
        try {

            ConfigService configService = NacosFactory.createConfigService(properties);
            String dataId = "Gridx_test";
            configService.addListener(dataId, group, new Listener() {
                public Executor getExecutor() {
                    return null;
                }

                public void receiveConfigInfo(String configInfo) {
                    System.out.println("update ：" + configInfo );
                }
            });


            while(true){

                String content = configService.getConfig(dataId, group, 5000);
                System.out.println(content);

                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试发布 config
     */
    @Test
    public void testConfigPublish(){
        try {

            ConfigService configService = NacosFactory.createConfigService(properties);

            String dataId = "leotest";
            String content = "hello=world";
            boolean result = configService.publishConfig(dataId, group,content);
            System.out.println(result);

        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试删除 config
     */
    @Test
    public void testConfigDelete(){
        try {

            ConfigService configService = NacosFactory.createConfigService(properties);

            String dataId = "leotest";
            boolean result = configService.removeConfig(dataId, group);
            System.out.println(result);

        } catch (NacosException e) {
            e.printStackTrace();
        }
    }


}
