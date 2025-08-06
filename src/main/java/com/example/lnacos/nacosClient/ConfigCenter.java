package com.example.lnacos.nacosClient;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

public class ConfigCenter {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "http://192.168.144.1:8848";
        //初始化配置了中心
        ConfigService configService = NacosFactory.createConfigService(serverAddr);

        //注册实例
        NamingService namingService = NacosFactory.createNamingService(serverAddr);
        for (Instance student : namingService.selectInstances("student", true)) { //获取服务器实例
            System.out.println(student.getServiceName() + " " + student.getIp() + " " + student.getPort() + " " + student.getInstanceId());
        }
        //监听服务
        Thread t = new Thread(()->{
            try {
                namingService.subscribe("student",(event) -> {
                    System.out.println(event.toString());
                    try {
                      for(Instance instance : namingService.selectInstances("student", true)){
                          System.out.println(instance.getServiceName()+" "+instance.getIp());
                      }
                    } catch (NacosException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (NacosException e) {
                throw new RuntimeException(e);
            }
        });
       t.start();
       t.join();
    }
}
