package com.zhengyun;


import org.dom4j.Element;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 听风 on 2018/1/1.
 */
public class WebXmlConfig {

    private ClassLoader webAppClassLoader;

    private ClassLoader parentJarClassLoader;

    public Map<String, String> servletUrl = new HashMap<String, String>();

    public ClassLoader getWebAppClassLoader() {

        loadParentClassLoader();

        if(webAppClassLoader == null){
            String path = System.getProperty(Global.CATALINA_HOME_PROP) + "/webapp/ROOT/WEB-INF/classes";
            File file = new File(path);
            try {
                URL url = new URL(file.toURI().toString());
                webAppClassLoader = URLClassLoader.newInstance(new URL[]{url}, parentJarClassLoader);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return webAppClassLoader;
    }

    private void loadParentClassLoader() {
        if(parentJarClassLoader == null){
            File directory=new File(System.getProperty(Global.CATALINA_HOME_PROP)  + "/webapp/ROOT/WEB-INF/lib");
            String[] filenames = directory.list();
            Set<URL> set = new HashSet<URL>();
            for(int i = 0; i < filenames.length; i++ ){
                String fn = filenames[i];
                if(fn.endsWith(".jar")){
                    File file = new File(directory, fn);
                    try {
                        URL url = new URL(file.toURI().toString());
                        set.add(url);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
            parentJarClassLoader = new URLClassLoader(set.toArray(new URL[set.size()]), ClassLoader.getSystemClassLoader());
        }
    }


    public String getClassName() {
        String className = null;
        String webXmlPath = System.getProperty(Global.CATALINA_HOME_PROP)  + "/webapp/ROOT/WEB-INF/web.xml";
        Degister degister = new Degister(webXmlPath);
        Element element = degister.getRoot();
        Element servlet = element.element("servlet");
        className = servlet.element("servlet-class").getStringValue();
        Element servletMap = element.element("servlet-mapping");
        String url = servletMap.element("url-pattern").getStringValue();
        servletUrl.put(className, url);
        return className;
    }
}
