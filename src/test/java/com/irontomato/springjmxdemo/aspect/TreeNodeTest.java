package com.irontomato.springjmxdemo.aspect;

import org.junit.Test;

public class TreeNodeTest {

    @Test
    public void test() {
        TreeNode node = new TreeNode();
        node
                .appendChild(new TreeNode()
                        .appendChild(
                                new TreeNode())
                        .appendChild(new TreeNode()))
                .appendChild(new TreeNode())
                .appendChild(new TreeNode());

        node.selfFirstEach(n -> System.out.println());
    }
}
