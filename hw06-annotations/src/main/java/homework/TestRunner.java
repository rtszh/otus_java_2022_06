package homework;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        final var testClass1 = TestList.class;
        final var testClass2 = TestList2.class;
        final var testClass3 = TestList3.class;
        final var testClass4 = TestList4.class;
        runTest(testClass1);
        runTest(testClass2);
        runTest(testClass3);
        runTest(testClass4);
    }

    private static void runTest(Class<?> testClass) throws Exception {
        var testProcessor1 = new TestProcessor(testClass);
        System.out.println("Start test for class: " + testClass.getSimpleName());
        System.out.println("_____________________");
        testProcessor1.startTest();
        System.out.println();
        System.out.println("Tests was completed for class: " + testClass.getSimpleName());
        System.out.println("_____________________");

    }
}
