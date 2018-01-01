package com.zhengyun;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 听风 on 2017/12/30.
 */
public class BootStrap {

    private static String CATALINA_HOME_PROP = "catalina.home";

    private static ClassLoader catalinaClassLoader = null;

    private static String DIR_LIB = "/lib";

    private Object startInstance;

    public void init() throws Exception{
        initCatalinaHome();

        initClassLoader();

        Thread.currentThread().setContextClassLoader(catalinaClassLoader);

        Class clazz = catalinaClassLoader.loadClass("com.zhengyun.Catalina");
        startInstance = clazz.newInstance();

    }

    public void start(){
        String methodName = "start";
        try {
            Method method = startInstance.getClass().getMethod(methodName, null);
            method.invoke(startInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initClassLoader() {
        File directory=new File(System.getProperty(CATALINA_HOME_PROP)  + DIR_LIB);
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
        catalinaClassLoader = new URLClassLoader(set.toArray(new URL[set.size()]));
    }

    private void initCatalinaHome() {
        try {
            System.setProperty
                    (CATALINA_HOME_PROP,
                            (new File(System.getProperty("user.dir"), ".."))
                                    .getCanonicalPath());
        }catch (Exception e){

        }

    }




    public static void main(String[] args) throws Exception{
        BootStrap bootStrap = new BootStrap();
        bootStrap.init();
        bootStrap.start();

        System.out.println(System.getProperty("java.class.path"));
    }
}
