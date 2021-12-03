package designPattern.factory;

public interface Logger {
    void writeLog();
}

class DatabaseLogger implements Logger{
    public void writeLog(){
        System.out.println("write log to database");
    }
}

class FileLogger implements Logger{
    @Override
    public void writeLog() {
        System.out.println("write log to file");
    }
}