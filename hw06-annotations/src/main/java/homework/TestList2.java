package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.util.List;

public class TestList2 {

    List<Integer> testData;

    @Before
    void createData() {
        testData = List.of(1, 3, 6, 9, 15, 19, 25, 37);
    }

    @Test
    void test1() throws Exception {
        if (testData.size() < 5) {
        } else throw new Exception();
    }

    @Test
    void test2() throws Exception {
        if (testData.size() > 10) {
        } else throw new Exception();
    }

    @Test
    void test3() throws Exception {
        if (testData.size() < 10) {
        } else throw new Exception();
    }

    @Test
    void test4() throws Exception {
        if (testData.size() > 5) {
        } else throw new Exception();
    }

    @After
    void testEndNotification() {
        System.out.println("The test was completed");
        System.out.println();
    }


}
