package proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invocation {
  public Method method;
  public Object object;
  public Object[] args;

  public Invocation(Object object,Method method,Object[] args){
    this.object = object;
    this.method = method;
    this.args = args;
  }

  public Object process() throws InvocationTargetException, IllegalAccessException {
     return method.invoke(object, args);
  }

 }
