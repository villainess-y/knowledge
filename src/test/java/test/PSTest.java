package test;

import juc.UtilsTest.JMH.PS;
import org.openjdk.jmh.annotations.*;

public class PSTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3)
    @Fork(5)
    @BenchmarkMode(Mode.Throughput)
    @Measurement(iterations = 1, time = 3)
    public void testForEach() {
        PS.foreach();
    }
}
