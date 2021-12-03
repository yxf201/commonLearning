package designPattern.prototype;

/**
 * @author yxf
 * @date 2021/12/3 14:48
 */
public class ConcretePrototype implements Prototype, Cloneable{
    private String attr;
    private Object obj = new Object();

    public void setAttr(String attr){
        this.attr = attr;
    }

    public String getAttr(){
        return this.attr;
    }

    @Override
    public Prototype prototypeClone() {
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setAttr(this.attr);
        return prototype;
    }

    @Override
    public Prototype clone(){
        Object obj = null;
        try{
            obj = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return (Prototype) obj;
    }

    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype();
        prototype.setAttr("aaa");
        Prototype p1 = prototype.prototypeClone();
        Prototype p2 = prototype.clone();
        System.out.println(prototype);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(prototype == p2);//false
        System.out.println(p1.getClass() == p2.getClass());//true
        System.out.println(prototype.attr == ((ConcretePrototype)p1).attr && ((ConcretePrototype)p1).attr == ((ConcretePrototype)p2).attr);//true
        System.out.println(prototype.obj == ((ConcretePrototype)p1).obj && ((ConcretePrototype)p1).obj == ((ConcretePrototype)p2).obj);//false
        System.out.println(prototype.obj == ((ConcretePrototype)p2).obj);//true
        Object obj = null;
        System.out.println((ConcretePrototype)obj);//null，不会报错
    }
}
