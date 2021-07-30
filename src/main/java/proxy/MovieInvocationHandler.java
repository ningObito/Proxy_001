package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MovieInvocationHandler implements InvocationHandler {
    public Object target;
    public Interceptor interceptor;
    public MovieInvocationHandler(Object target, Interceptor interceptor){
      this.target = target;
      this.interceptor = interceptor;
    }


  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      return interceptor.intercept(new Invocation(target, method, args));
  }


  public static Object wrapper(Object object,Interceptor interceptor){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
     object.getClass().getInterfaces(), new MovieInvocationHandler(object, interceptor));

  }
}
