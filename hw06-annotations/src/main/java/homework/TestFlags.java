package homework;

public class TestFlags {
    boolean beforeFlag;
    boolean testFlag;

    public TestFlags() {
        beforeFlag = true;
        testFlag = true;
    }

    public void setBeforeFlagFalse() {
        beforeFlag = false;
    }

    public void setBeforeFlagTrue() {
        beforeFlag = true;
    }

    public boolean getBeforeFlag() {
        return beforeFlag;
    }

    public void setTestFlagFalse() {
        testFlag = false;
    }

    public void setTestFlagTrue() {
        testFlag = true;
    }

    public boolean getTestFlag() {
        return testFlag;
    }

}
