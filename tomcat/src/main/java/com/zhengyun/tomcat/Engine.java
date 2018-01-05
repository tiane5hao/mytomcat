package com.zhengyun.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2017/12/31.
 */
public class Engine implements Container {

    private Host hosts;

    private List<Valve> valueList;

    public void init() {

    }

    public void start() {
        hosts.start();
    }

    public void distory() {

    }

    public void addHost(Object obj) {
        Host host = (Host) obj;
        this.hosts = host;
    }

    public void addValue(Object obj){
        if(obj instanceof Valve){
            Valve value = (Valve) obj;
            if(valueList == null){
                valueList = new ArrayList<Valve>(1);
            }
            valueList.add(value);
        }

    }

}
