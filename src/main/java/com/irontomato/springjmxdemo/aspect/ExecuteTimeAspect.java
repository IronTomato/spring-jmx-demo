package com.irontomato.springjmxdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

@Component
@Aspect
@ManagedResource
public class ExecuteTimeAspect {

    //    @Value("${springjmxdemo.executetime.threshold}")
    private long threshold = 0;

    @ManagedAttribute
    public long getThreshold() {
        return threshold;
    }

    @ManagedOperation
    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }

    private ThreadLocal<ExecutionNode> currentNode = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(public * com.irontomato.springjmxdemo.controller.*.*(..))")
    public Object logExecuteTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;
        Throwable ex = null;
        boolean exEnd = false;

        if (currentNode.get() == null) {
            currentNode.set(new ExecutionNode());
        } else {
            currentNode.get().appendChild(new ExecutionNode());
            currentNode.set((ExecutionNode) currentNode.get().getChildrenTail());
        }
        long start = System.currentTimeMillis();
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            ex = e;
            exEnd = true;
        }
        long execTime = System.currentTimeMillis() - start;

        String methodFullName = joinPoint.getSignature().toString();

        currentNode.get().setExecutionInfo(new ExecutionInfo(execTime, methodFullName, exEnd));
        if (currentNode.get().getParent() == null && execTime > threshold) {
            currentNode.get().selfFirstEach(n -> logger.info(n.treeString()));
        }
        currentNode.set((ExecutionNode) currentNode.get().getParent());

        if (exEnd) {
            throw ex;
        }
        return result;
    }
}
