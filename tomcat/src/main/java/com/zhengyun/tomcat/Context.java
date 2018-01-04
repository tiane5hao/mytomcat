package com.zhengyun.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2017/12/31.
 */
public class Context {

    private List<Valve> valueList;

    public void addValue(Object obj){
        Valve value = (Valve)obj;
        if(valueList == null){
            valueList = new ArrayList<Valve>(1);
        }
        valueList.add(value);
    }
}
