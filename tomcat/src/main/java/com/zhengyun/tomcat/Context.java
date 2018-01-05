package com.zhengyun.tomcat;

import com.zhengyun.WebXmlConfig;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2017/12/31.
 */
public class Context implements Container{

    private List<Valve> valueList;

    private Servlet servlet;

    public void addValue(Object obj){
        Valve value = (Valve)obj;
        if(valueList == null){
            valueList = new ArrayList<Valve>(1);
        }
        valueList.add(value);
    }

    public Servlet createServlet(){
        if(servlet != null){
            return servlet;
        }

        WebXmlConfig webXmlConfig = new WebXmlConfig();
        ClassLoader classLoader = webXmlConfig.getWebAppClassLoader();
        String className = webXmlConfig.getClassName();
        try {
            Class clazz = classLoader.loadClass(className);
            servlet = (Servlet) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(servlet.getClass().getName());
        return servlet;
    }

    public void init() {
        createServlet();
    }

    public void start() {

    }

    public void distory() {

    }
}
