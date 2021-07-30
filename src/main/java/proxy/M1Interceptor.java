package proxy;

public class M1Interceptor implements  Interceptor{

  @Override
  public void before(Invocation invocation) {
    System.out.println("买些爆米花和可乐");
  }

  @Override
  public void after(Invocation invocation) {
    System.out.println("带走垃圾");
  }
}
