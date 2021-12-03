package designPattern.singleton;

/**
 * @author yxf
 * @date 2021/12/1 15:31
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {}

    public synchronized static LazySingleton getInstance1(){
        if( instance == null ){
            instance = new LazySingleton();
        }
        return instance;
    }

    public static LazySingleton getInstance2(){
        if( instance == null ){
            synchronized (LazySingleton.class){
                instance = new LazySingleton();
            }
        }
        return instance;
    }

    private volatile static LazySingleton volatileInstance = null;

    public static LazySingleton doubleCheckGetInstance(){
        if( volatileInstance == null ){
            synchronized (LazySingleton.class){
                if( volatileInstance == null ){
                    volatileInstance = new LazySingleton();
                }
            }
        }
        return volatileInstance;
    }
}
