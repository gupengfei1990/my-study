package first.week.problems.second;

import java.lang.reflect.Method;

public class MainClass {

    public static void main(String[] args) throws Exception {
        System.out.println("程序开始");
        Class<?> helloClass = new MyClassLoader().findClass("Hello.xlass");
        Method helloMethod = helloClass.getMethod("hello");
        helloMethod.invoke(helloClass.newInstance());
        System.out.println("程序结束");
    }
}
