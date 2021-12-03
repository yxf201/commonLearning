package designPattern.singleton;

/**
 * @author yxf
 * @date 2021/12/1 10:40
 */
public class TaskManager {
    private static TaskManager instance;

    private TaskManager(){
        System.out.println("task manager init");
    }

    public void displayProcesses(){
        System.out.println("show processed");
    }

    public void displayServices(){
        System.out.println("show services");
    }

    public static TaskManager getInstance(){
        if( instance == null ){
            instance = new TaskManager();
        }
        return instance;
    }
}
