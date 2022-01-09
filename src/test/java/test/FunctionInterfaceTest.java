package test;


public class FunctionInterfaceTest {

    public static class A<T> {
        T t;
        public void print() {
            System.out.println(t);
        }
        public A(T t){
            this.t = t;
        }
    }

    public static void main(String[] args) {
        A<String> a = new A<>("helloWorld");
        a.print();
    }
}
