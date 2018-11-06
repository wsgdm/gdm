package gdm.myproxy.jdk.myjdk;

import java.lang.reflect.Method;

public interface GdInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
