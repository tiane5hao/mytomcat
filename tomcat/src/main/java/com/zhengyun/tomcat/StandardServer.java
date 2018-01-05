package com.zhengyun.tomcat;

import com.zhengyun.tomcat.listerner.Listerner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2017/12/30.
 */
public class StandardServer implements Server, LifeCycle {

    private List<Service> serviceList = null;

    ArrayList<Listerner> listernerList = null;

    public void init() {
        System.out.println("StandardServer init finish...");
        for(Service service : serviceList){
            service.init();
        }
    }

    public void start() {
        for(Service service : serviceList){
            service.start();
        }
    }

    public void distory() {

    }

    public Server addService(Object obj){
        if(obj instanceof Service){
            Service service = (Service)obj;
            if(serviceList == null){
                serviceList = new ArrayList<Service>(1);
            }
            serviceList.add(service);
        }
        return this;
    }

    public Server addListener(Object obj) {
        if(obj instanceof Listerner){
            Listerner listerner = (Listerner)obj;
            if(listernerList == null){
                listernerList = new ArrayList<Listerner>(1);
            }
            listernerList.add(listerner);
        }
        return this;
    }
}
