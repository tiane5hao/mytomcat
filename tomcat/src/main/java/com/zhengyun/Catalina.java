package com.zhengyun;

/**
 * Created by 听风 on 2018/1/1.
 */
public class Catalina {

    public void start(){
        System.out.println("Catalina.start");

        setClasspath();
    }

    private void setClasspath() {
        System.setProperty("java.class.path", "/webapp/ROOT/WEB-INF");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
    }

}
