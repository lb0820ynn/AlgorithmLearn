package learn.design;

import java.lang.reflect.*;

/**
 * Created by liubin on 2020/4/4.
 */
public class ProxyTest {

    public static void main(String[] args) throws NoSuchFieldException {
//        BuyCar audi = new BuyAudi(baseUrl);
//        BuyCarProxy proxy = new BuyCarProxy(audi);
//        proxy.buyCar();
//
//        BuyCar buyCar = (BuyCar) Proxy.newProxyInstance(BuyCar.class.getClassLoader(), new Class[]{BuyCar.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("我是动态代理");
//                Object result = method.invoke(BuyAudi.class.newInstance(), args);
//                System.out.println("我动态代理买车结束");
//                return result;
//            }
//        });
//        buyCar.buyCar();

        BuyAudi b = new BuyAudi("sssss");
        Field baseUrl = b.getClass().getDeclaredField("baseUrl");
        try {
            updateFinalModifiers(baseUrl);
            baseUrl.set(b, "aaaa");
            System.out.println(b.baseUrl);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void updateFinalModifiers(Field field) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
    }

}
