package com.zhengyun;

import com.zhengyun.tomcat.Server;
import com.zhengyun.tomcat.StandardServer;

/**
 * Created by 听风 on 2018/1/1.
 */
public class Catalina {

    private Server server;

    public void start(){
        System.out.println("Catalina.start");
        createServer();
        server.init();
        server.start();
    }

    private void createServer(){
        String serverPath = System.getProperty(Global.CATALINA_HOME_PROP) + "/conf/server.xml";
        System.out.println(serverPath);
        Degister degister = new Degister(serverPath);
        degister.addClassName("Server", "com.zhengyun.tomcat.StandardServer");
        degister.addClassName("Service", "com.zhengyun.tomcat.StandardService");
        degister.addClassName("Connector", "com.zhengyun.tomcat.HttpConnector");
        degister.addClassName("Engine", "com.zhengyun.tomcat.Engine");
        degister.addClassName("Host", "com.zhengyun.tomcat.Host");
        degister.addClassName("Context", "com.zhengyun.tomcat.Context");
        server = (Server) degister.createRootObject();
    }

    public static void main(String[] args) {
        Catalina catalina = new Catalina();
        catalina.start();
        System.out.println(catalina.server);
    }
}
