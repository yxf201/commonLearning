package designPattern.factory;

public abstract class LoggerFactory {
    public abstract Logger createLogger();

    public abstract Logger createLogger(String args);

    public abstract Logger createLogger(Object obj);

    public void writeLog(){
        Logger logger = this.createLogger();
        logger.writeLog();
    }
}

class DataLoggerFactory extends LoggerFactory{

    @Override
    public Logger createLogger() {
        //连接数据库，代码省略
        //创建数据库日志记录器对象
        Logger logger = new DatabaseLogger();
        //初始化数据库日志记录器，代码省略
        return logger;
    }

    @Override
    public Logger createLogger(String args) {
        return null;
    }

    @Override
    public Logger createLogger(Object obj) {
        return null;
    }
}

