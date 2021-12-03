package designPattern.singleton;

/**
 * @author yxf
 * @date 2021/12/1 14:27
 */
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() {}

    public static EagerSingleton getInstance(){
        return instance;
    }
}
