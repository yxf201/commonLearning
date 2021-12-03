package designPattern.simpleFactory;

public class ChartFactory {
    public static Chart getChart(String type){
        Chart chart = null;
        if( "ChartA".equalsIgnoreCase(type) ){
            chart = new ChartA();
        }else if( "ChartB".equalsIgnoreCase(type) ){
            chart = new ChartB();
        }else if( "ChartC".equalsIgnoreCase(type) ){
            chart = new ChartC();
        }else{
            chart = new DefaultChart();
        }
        return chart;
    }

    //class client
    public static void main(String[] args) {
        Chart chart = ChartFactory.getChart("ChartC");
        chart.display();
    }
}

interface Chart{
    void display();
}

class DefaultChart implements Chart{
    public DefaultChart(){
        System.out.println("DefaultChart create");
    }

    @Override
    public void display() {
        System.out.println("display DefaultChart");
    }
}

class ChartA implements Chart{
    public ChartA(){
        System.out.println("ChartA create");
    }

    @Override
    public void display() {
        System.out.println("display ChartA");
    }
}

class ChartB implements Chart{
    public ChartB(){
        System.out.println("ChartB create");
    }

    @Override
    public void display() {
        System.out.println("ChartB ChartB");
    }
}

class ChartC implements Chart{
    public ChartC(){
        System.out.println("ChartC create");
    }

    @Override
    public void display() {
        System.out.println("display ChartC");
    }
}

class A{
    static void a(){}
}

class B extends A{
    static void b(){
        B.a();
    }
}
