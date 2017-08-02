package com.irontomato.springjmxdemo.aspect;

import static org.apache.commons.lang3.StringUtils.*;

public class ExecutionNode extends TreeNode {

    private ExecutionInfo executionInfo;

    public ExecutionInfo getExecutionInfo() {
        return executionInfo;
    }

    public void setExecutionInfo(ExecutionInfo executionInfo) {
        this.executionInfo = executionInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(leftPad(executionInfo.getExecTime() + "ms", 7)).append("]");
        if (getParent() == null) {
            sb.append("[100%] ");
        } else {
            ExecutionNode p = (ExecutionNode) getParent();
            sb.append("[")
                    .append(leftPad((this.executionInfo.execTime * 100L / p.getExecutionInfo().getExecTime()) + "%", 4))
                    .append("] ");
        }
        sb.append(executionInfo.isExEnd() ? "E " : "N ")
                .append(executionInfo.getMethodFullName());
        return sb.toString();
    }
}
