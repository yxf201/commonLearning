package designPattern.abstractFactory;

import designPattern.utils.XMLUtil;
import org.junit.jupiter.api.Test;

/**
 * @author yxf
 * @date 2021/11/30 16:01
 */
public class Client {
    public static void main(String[] args) {
        SkinFactory factory;
        Button button;
        TextField textField;
        ComboBox comboBox;

        factory = (SkinFactory) XMLUtil.getBeanByClassName("C:\\\\Users\\\\admin\\\\Desktop\\\\temp\\\\config.xml");
        button = factory.createButton();
        textField = factory.createTextField();
        comboBox = factory.createComboBox();

        button.display();
        textField.display();
        comboBox.display();
    }

    @Test
    public void testGameControllerFactory(){
        GameFactory factory;
        OperationController operationController;
        InterfaceController interfaceController;

        factory = (GameFactory) XMLUtil.getBeanByClassName("C:\\\\Users\\\\admin\\\\Desktop\\\\temp\\\\config.xml");
        operationController = factory.createOperationController();
        interfaceController = factory.createInterfaceController();

        operationController.control();
        interfaceController.control();
    }
}
