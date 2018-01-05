package com.zhengyun.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2017/12/31.
 */
public class StandardService implements Service, LifeCycle {

    List<Connector> connectorList = null;

    Engine engine;

    public void init() {
        engine.start();
    }

    public void start() {

    }

    public void distory() {

    }

    public void addEngine(Object obj){
        Engine engine = (Engine)obj;
        this.engine = engine;
    }

    public void addConnector(Object obj) {
        Connector connector = (Connector)obj;
        if(connectorList == null){
            connectorList = new ArrayList<Connector>(1);
        }
        connectorList.add(connector);
    }
}
