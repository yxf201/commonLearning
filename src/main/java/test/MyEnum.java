package test;

public enum MyEnum implements IEnum {
    ONE(1) {
        @Override
        public void test() {
            System.out.println(1);
        }

        @Override
        public void what() {
            System.out.println("what 1");
        }
    },
    TWO(2) {
        @Override
        public void what() {
            System.out.println("what 2");
        }
    };
    private int num;

    MyEnum(int num) {
        this.num = num;
    }

    abstract public void what();

    @Override
    public void test() {
        System.out.println("test");
    }

    public static void main( String args[]){
        ONE.test();
        TWO.test();
    }
}

interface IEnum {
    void test();
}

