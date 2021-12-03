package designPattern.abstractFactory;

/**
 * @author yxf
 * @date 2021/11/30 15:55
 */
public interface Button {
    void display();
}

class SpringButton implements Button{

    @Override
    public void display() {
        System.out.println("show spring button");
    }
}

class SummerButton implements Button{
    @Override
    public void display() {
        System.out.println("show summer button");
    }
}

interface TextField{
    void display();
}

class SpringTextField implements TextField{
    @Override
    public void display() {
        System.out.println("show spring text field");
    }
}

class SummerTextField implements TextField{
    @Override
    public void display() {
        System.out.println("show summer text field");
    }
}

interface ComboBox{
    void display();
}

class SpringComboBox implements ComboBox{

    @Override
    public void display() {
        System.out.println("show spring combo box");
    }
}

class SummerComboBox implements ComboBox{

    @Override
    public void display() {
        System.out.println("show summer combo box");
    }
}

interface SkinFactory{
    Button createButton();
    TextField createTextField();
    ComboBox createComboBox();
}



