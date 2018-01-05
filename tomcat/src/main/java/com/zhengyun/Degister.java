package com.zhengyun;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Degister{

    private Element root;

    private ClassLoader classLoader;

    private Map<String, String> classMap = new HashMap<String, String>();

    private ClassLoader getClassLoader(){
        if(classLoader != null){
            return classLoader;
        }
        classLoader = this.getClass().getClassLoader();
        return classLoader;
    }

    public Degister(String path){
        parseXML(path);
    }

    private Element parseXML(String path){
        File myXML = new File(path);
        SAXReader sr = new SAXReader();
        Element root = null;
        try {
            Document doc  =  sr.read(myXML);
            root = doc.getRootElement();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        this.root = root;
        return root;
    }

    public Element getRoot(){
        return root;
    }

    public Degister addClassName(String nodeName, String className){
        classMap.put(nodeName, className);
        return this;
    }

    public Object createRootObject(){

        String name = root.getQName().getName();
        String className = classMap.get(name);
        Object rootObj = createObject(className);
        installChildObj(root.elements(), rootObj);
        return rootObj;
    }

    private void installChildObj(List<Element> elements, Object parentObj) {
        if(elements == null){
            return;
        }

        for(Element element : elements){
            String name = element.getQName().getName();
            Attribute attribute = element.attribute("className");
            String className = null;
            if(attribute != null && attribute.getValue() != null){
                className = attribute.getValue();
            }else {
                className = classMap.get(name);
            }
            Object child = createObject(className);
            installChildObj(element.elements(), child);

            String methodName = "add" + name;
            try {
                Method method = parentObj.getClass().getMethod(methodName, Object.class);
                method.invoke(parentObj, child);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Object createObject(String className){
        try {
            Class clazz = getClassLoader().loadClass(className);
            if(clazz == null){
                clazz = ClassLoader.getSystemClassLoader().loadClass(className);
            }
            return clazz.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {


    }
}
