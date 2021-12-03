package designPattern.abstractFactory;

/**
 * @author yxf
 * @date 2021/11/30 17:02
 */
public interface GameFactory {
    OperationController createOperationController();
    InterfaceController createInterfaceController();
}

interface OperationController{
    void control();
}

interface InterfaceController{
    void control();
}

class SymbianOperationController implements OperationController{

    @Override
    public void control() {
        System.out.println("control operation in Symbian");
    }
}

class AndroidOperationController implements OperationController{

    @Override
    public void control() {
        System.out.println("control operation in Android");
    }
}

class WindowsMobileOperationController implements OperationController{

    @Override
    public void control() {
        System.out.println("control operation in Windows Mobile");
    }
}

class SymbianInterfaceController implements InterfaceController{

    @Override
    public void control() {
        System.out.println("control interface in Symbian");
    }
}

class AndroidInterfaceController implements InterfaceController{

    @Override
    public void control() {
        System.out.println("control interface in Android");
    }
}

class WindowsMobileInterfaceController implements InterfaceController{

    @Override
    public void control() {
        System.out.println("control interface in Windows Mobile");
    }
}

class SymbianFactory implements GameFactory{

    @Override
    public OperationController createOperationController() {
        return new SymbianOperationController();
    }

    @Override
    public InterfaceController createInterfaceController() {
        return new SymbianInterfaceController();
    }
}

class AndroidFactory implements GameFactory{

    @Override
    public OperationController createOperationController() {
        return new AndroidOperationController();
    }

    @Override
    public InterfaceController createInterfaceController() {
        return new AndroidInterfaceController();
    }
}

