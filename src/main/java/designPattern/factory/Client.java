package designPattern.factory;

import designPattern.utils.XMLUtil;

public class Client {
    public static void main(String[] args) {
        LoggerFactory factory;
        Logger logger;

        // factory = new FileLoggerFactory();
        factory = (LoggerFactory) XMLUtil.getBeanByClassName("C:\\Users\\admin\\Desktop\\temp\\config.xml");

        /*logger = factory.createLogger();

        logger.writeLog();*/

        factory.writeLog();
    }
}
