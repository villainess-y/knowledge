package jdk8NewFunction.defaultKey;

public interface InterfaceA {
    default void helloWorld(){
        System.out.println("Hi,I'm from InterfaceA!");
    }
}
