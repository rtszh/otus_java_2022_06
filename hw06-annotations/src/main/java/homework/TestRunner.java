package homework;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        var testClass1 = TestList.class;
        TestProcessor testProcessor1 = new TestProcessor(testClass1);
        System.out.println("Start test for class: " + testClass1.getSimpleName());
        System.out.println("_____________________");
        testProcessor1.startTest();
        System.out.println();
        System.out.println("Tests was completed for class: " + testClass1.getSimpleName());
        System.out.println("_____________________");



        var testClass2 = TestList2.class;
        TestProcessor testProcessor2 = new TestProcessor(testClass2);
        System.out.println("Start test for class: " + testClass2.getSimpleName());
        System.out.println("_____________________");
        testProcessor2.startTest();
        System.out.println();
        System.out.println("Tests was completed for class: " + testClass2.getSimpleName());
        System.out.println("_____________________");

    }
}
