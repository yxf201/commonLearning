package designPattern.simpleFactory;

public class Practice {

}

class ShapeFactory{
    Shape getInstance(String type) throws UnSupportedShapeException {
        Shape shape = null;
        if( "A".equalsIgnoreCase(type) ){
            shape = new ShapeA();
        }else if( "B".equalsIgnoreCase(type) ){
            shape = new ShapeB();
        }else if( "C".equalsIgnoreCase(type) ){
            shape = new ShapeC();
        }else{
            throw new UnSupportedShapeException();
        }
        return shape;
    }
}

class UnSupportedShapeException extends Exception{

}

interface Shape{
    void draw();
    void erase();
}

class ShapeA implements Shape{

    @Override
    public void draw() {

    }

    @Override
    public void erase() {

    }
}

class ShapeB implements Shape{

    @Override
    public void draw() {

    }

    @Override
    public void erase() {

    }
}

class ShapeC implements Shape{

    @Override
    public void draw() {

    }

    @Override
    public void erase() {

    }
}
