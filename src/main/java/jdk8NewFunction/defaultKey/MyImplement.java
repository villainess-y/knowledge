package jdk8NewFunction.defaultKey;

public class MyImplement implements InterfaceA,InterfaceB{
    @Override
    public void helloWorld() {
        System.out.println("yeah, i'm from MyImplement");
    }

    public static void main(String[] args) {
        MyImplement myImplement = new MyImplement();
        myImplement.helloWorld();
    }
}
