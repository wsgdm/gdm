package gdm.myproxy.jdk;

import java.lang.reflect.Method;

public interface GdInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
