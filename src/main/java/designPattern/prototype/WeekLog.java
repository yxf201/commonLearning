package designPattern.prototype;

/**
 * @author yxf
 * @date 2021/12/3 15:17
 */
public class WeekLog implements Cloneable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String name;
    private String date;
    private String content;

    @Override
    public WeekLog clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (WeekLog) obj;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        WeekLog log_previous = new WeekLog();  //创建原型对象

        log_previous.setName("张无忌");

        log_previous.setDate("第12周");

        log_previous.setContent("这周工作很忙，每天加班！");


        System.out.println("****周报****");

        System.out.println("周次：" + log_previous.getDate());

        System.out.println("姓名：" + log_previous.getName());

        System.out.println("内容：" + log_previous.getContent());

        System.out.println("--------------------------------");


        WeekLog log_new;

        log_new = log_previous.clone(); //调用克隆方法创建克隆对象

        log_new.setDate("第13周");

        System.out.println("****周报****");

        System.out.println("周次：" + log_new.getDate());

        System.out.println("姓名：" + log_new.getName());

        System.out.println("内容：" + log_new.getContent());


        System.out.println(log_previous == log_new);

        System.out.println(log_previous.getDate() == log_new.getDate());

        System.out.println(log_previous.getName() == log_new.getName());

        System.out.println(log_previous.getContent() == log_new.getContent());
    }
}
