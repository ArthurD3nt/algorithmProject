package graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @Author Francesco Mazzucco
 */

public class StringGraphTestsRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StringGraphTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
