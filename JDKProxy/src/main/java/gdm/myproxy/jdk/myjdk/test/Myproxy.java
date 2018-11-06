package gdm.myproxy.jdk.myjdk.test;

import gdm.myproxy.jdk.myjdk.GdClassLoader;
import gdm.myproxy.jdk.myjdk.GdInvocationHandler;
import gdm.myproxy.jdk.myjdk.GdProxy;

import java.lang.reflect.Method;

public class Myproxy implements GdInvocationHandler {

    private Object obj;

    private String[] MethodsName;
    public Object getInstance(Object obj ,String[] MethodsName){
        this.obj = obj;
        this.MethodsName = MethodsName;
        Class clazz = obj.getClass();
        return  GdProxy.newProxyInstance(new GdClassLoader(),clazz.getInterfaces(),this,this.MethodsName,this.obj);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前.............");
        method.invoke(this.obj,args);
        System.out.println("代理后.............");
        return null;
    }
}
