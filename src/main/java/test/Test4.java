package test;

public class Test4 {
    public static void main(String[] args) {
        B b = new B(2);
    }


}

class A{
    int a;
    A(int a){
        this.a = 0;
        System.out.println(this.a);
    }
}

class B extends A{
    int a;

    B(int a) {
        super(a);
        this.a = 2*a;
        System.out.println(this.a);
    }

    public boolean buddyStrings(String s, String goal) {
        if( s.length() != goal.length() ){
            return false;
        }

        int first = -1, second = -1;
        for( int i = 0; i < s.length(); i++ ){
            char c1 = s.charAt(i);
            char c2 = goal.charAt(i);
            if( c1 != c2 ){
                if( first == -1 ){
                    first = i;
                }else if( second == -1 ){
                    second = i;
                }else{
                    return false;
                }
            }
        }

        if( first != -1 && second != -1 ){
            return s.charAt(first) == goal.charAt(second) && goal.charAt(first) == s.charAt(second);
        }else if( first == -1 && second == -1 ){
            int[] count = new int[26];
            for( Character c : s.toCharArray() ){
                if( ++count[c-'a'] > 1 ){
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
}

