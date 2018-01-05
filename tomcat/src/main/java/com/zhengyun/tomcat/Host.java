package com.zhengyun.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2017/12/31.
 */
public class Host implements Container{

    private Context context;

    private List<Valve> valueList;

    public void addContent(Object obj){
        Context context = (Context)obj;
        this.context = context;
    }

    public void init() {
        if(context == null){
            context = new Context();
        }
        context.init();
    }

    public void start() {

    }

    public void distory() {

    }

    public void addValve(Object obj){
        Valve value = (Valve) obj;
        if(valueList == null){
            valueList = new ArrayList<Valve>(1);
        }
        valueList.add(value);
    }
}
