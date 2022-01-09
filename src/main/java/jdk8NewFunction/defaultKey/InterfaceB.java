package jdk8NewFunction.defaultKey;

public interface InterfaceB {
    default void helloWorld(){
        System.out.println("Hello,I'm from InterfaceB!");
    }
}
