package com.irontomato.springjmxdemo.aspect;

public class ExecutionInfo {
    long execTime;
    String methodFullName;
    boolean exEnd;

    public ExecutionInfo(long execTime, String methodFullName, boolean exEnd) {
        this.execTime = execTime;
        this.methodFullName = methodFullName;
        this.exEnd = exEnd;
    }

    public long getExecTime() {
        return execTime;
    }

    public void setExecTime(long execTime) {
        this.execTime = execTime;
    }

    public String getMethodFullName() {
        return methodFullName;
    }

    public void setMethodFullName(String methodFullName) {
        this.methodFullName = methodFullName;
    }

    public boolean isExEnd() {
        return exEnd;
    }

    public void setExEnd(boolean exEnd) {
        this.exEnd = exEnd;
    }
}
