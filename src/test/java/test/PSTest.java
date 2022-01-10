package test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import juc.UtilsTest.JMH.PS;
import org.openjdk.jmh.annotations.*;

public class PSTest {

//    @Benchmark
//    @Warmup(iterations = 1, time = 3)
//    @Fork(5)
//    @BenchmarkMode(Mode.Throughput)
//    @Measurement(iterations = 1, time = 3)
//    public void testForEach() {
//        PS.foreach();
//    }


    int age;
    Boolean success;

    public PSTest(int age, Boolean success) {
        this.age = age;
        this.success = success;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static int test(){
        int[] arr = new int[1];
        try {
            arr[0] = 1;
            return arr[0];
        }catch (Exception e){

        }finally {
            arr[0] = 2;
            return arr[0];
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(test());
    }
}
