package proxy;

public class M2Interceptor implements Interceptor{

  @Override
  public void before(Invocation invocation) {
    System.out.println("先把手机调成静音");
  }

  @Override
  public void after(Invocation invocation) {
    System.out.println("把手机调成正常模式");
  }
}
