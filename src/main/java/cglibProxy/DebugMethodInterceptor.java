package cglibProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class DebugMethodInterceptor implements MethodInterceptor {
    /**
     * @param o           代理对象（增强的对象）（被代理对象的子类）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method: " + method);
        System.out.println("Object o: " + o.getClass());
        //System.out.println("before method " + method.getName());
        //System.out.println("method: " + method);
        Object res = methodProxy.invokeSuper(o, args);
        System.out.println("after method: " + method);
        return res;
    }

}
