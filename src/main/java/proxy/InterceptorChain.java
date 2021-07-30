package proxy;

import java.util.ArrayList;
import java.util.List;

public class InterceptorChain {
  private  List<Interceptor> list = new ArrayList<>();

  public void addInterceptor(Interceptor interceptor){
      list.add(interceptor);
  }

  public List<Interceptor> getInterceptorList(){
    return list;
  }

  public Object pluginALl(Object object){
    for(Interceptor interceptor : list){
        object = interceptor.plugin(object);
    }
    return  object;
  }

}
