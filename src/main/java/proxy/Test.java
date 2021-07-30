package proxy;

public class Test {
  public static void main(String[] args) {

    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

    /*
        该测试类类似于mybatis中configuration类, configuration类中保存一个interceptor对象
        用来保存所有的plugin.
     */

    InterceptorChain interceptorChain = new InterceptorChain();

    /*
      具体的业务，例如mybatis中的Executor,StatementHandler
      ParameterHandler,ResultSetHandler 即需要代理的对象
     */

    MovieService target = new MovieServiceImpl();


    /*
       类似于解析mybatis-config.xml中的<plugin>/</plugin>
       然后生成对应的plugin对象，然后保存在interceptorChain中
     */
    interceptorChain.addInterceptor(new M2Interceptor());
    interceptorChain.addInterceptor(new M1Interceptor());

    /*
       采用责任链和动态代理的设计模式,最后产生一个动态代理的对象

        1. 第一次需要代理的对象是 target = new MovieServiceImpl()  ---产生的代理对象是
           M1Interceptor m1Interceptor = new M1Interceptor();
           MovieInvocationHandler handler1 = new MovieInvocationHandler(target1, m1Interceptor);
           target1 = new $Proxy0(handler1);

        2. 第二次需要代理的对象是target1  --- 产生的代理对象是
           M1Interceptor m2Interceptor = new M2Interceptor();
           MovieInvocationHandler handler2 = new MovieInvocationHandler(target1, m2Interceptor);
           target2 = new $Proxy0(handler2);

        3. target = target2  即target2是最后返回的代理对象

     */
    target = (MovieService) interceptorChain.pluginALl(target);

    /*
      调用过程
       1. target2.watchMovie() --> handler2.invoke()--> m2Interceptor.interceptor()-->m2Interceptor.before()

       2. target1.watchMovie() --> handler1.invoke()--> m1Interceptor.interceptor()-->m1Interceptor.before()

       3. target.watchMovie()

       4. m1Interceptor.after()

       5. m2Interceptor.after()
     */

    target.watchMovie();

  }
}
