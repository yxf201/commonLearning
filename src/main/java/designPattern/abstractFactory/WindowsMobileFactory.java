package designPattern.abstractFactory;

/**
 * @author yxf
 * @date 2021/11/30 17:12
 */
public class WindowsMobileFactory implements GameFactory {

    @Override
    public OperationController createOperationController() {
        return new WindowsMobileOperationController();
    }

    @Override
    public InterfaceController createInterfaceController() {
        return new WindowsMobileInterfaceController();
    }
}
