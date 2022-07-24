package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.util.List;

public class TestList {

    List<Integer> testData;

    @Before
    void createData() {
        testData = List.of(1, 3, 6, 9, 15, 19);
    }

    @Test
    void test1() throws Exception {
        if(testData.size() < 7) {
        } else throw new Exception();
    }

    @Test
    void test2() throws Exception {
        if(testData.size() > 7) {
        } else throw new Exception();
    }

    @After
    void testEndNotification() {
        System.out.println("The test was completed");
        System.out.println();
    }


}
