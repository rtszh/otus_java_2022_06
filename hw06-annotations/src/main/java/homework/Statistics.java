package homework;

public class Statistics {
    private int passedTests;
    private int failedTests;
    private int sumTests;

    public void incrementPassedTests() {
        passedTests++;
        sumTests++;
    }

    public void incrementFailedTests() {
        failedTests++;
        sumTests++;
    }

    public int getPassedTests() {
        return passedTests;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public int getSumTests() {
        return sumTests;
    }

}
