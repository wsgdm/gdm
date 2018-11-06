package gdm.proxy.jdk;

import gdm.myproxy.jdk.GdClassLoader;
import gdm.myproxy.jdk.GdInvocationHandler;
import gdm.myproxy.jdk.GdProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Myproxy implements GdInvocationHandler {

    private Object obj;

    public Object getInstance(Object obj){
        this.obj = obj;
        Class clazz = obj.getClass();
        return  GdProxy.newProxyInstance(new GdClassLoader(),clazz.getInterfaces(),this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前.............");
        method.invoke(this.obj,args);
        System.out.println("代理后.............");
        return null;
    }
}
