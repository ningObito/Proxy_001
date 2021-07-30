package proxy;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {
  // default function
  default Object plugin(Object object){
    return MovieInvocationHandler.wrapper(object, this);
  }

  // default function
  default public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
    before(invocation);
    Object object = invocation.process();
    after(invocation);
    return object;
  }

  //拦截器在业务之前需要完成的逻辑,是否需要传入参数，可以根据具体情况来定
  void before(Invocation invocation);

  //拦截器在业务之后需要完成的逻辑,是否需要传入参数，可以根据具体情况来定
  void after(Invocation invocation);

}
