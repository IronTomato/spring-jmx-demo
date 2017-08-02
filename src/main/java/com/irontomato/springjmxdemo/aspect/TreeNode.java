package com.irontomato.springjmxdemo.aspect;

import java.util.function.Consumer;

public class TreeNode {

    private TreeNode parent;

    private TreeNode childrenHead;

    private TreeNode childrenTail;

    private TreeNode previous;

    private TreeNode next;

    public TreeNode appendChild(TreeNode newChild) {
        if (newChild.next != null) {
            throw new IllegalArgumentException("new child should not have a next sibling.");
        }
        newChild.parent = this;
        if (childrenHead == null) {
            childrenHead = newChild;
            childrenTail = newChild;
        } else {
            childrenTail.next = newChild;
            newChild.previous = childrenTail;
            childrenTail = newChild;
        }
        return this;
    }

    public void selfFirstEach(Consumer<TreeNode> consumer) {
        consumer.accept(this);
        TreeNode cursor = childrenHead;
        while (cursor != null) {
            cursor.selfFirstEach(consumer);
            cursor = cursor.next;
        }
    }

    public String treeString() {
        String str = toString();
        if (parent == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (parent.childrenTail == this) {
            sb.append(" -\\");
        } else {
            sb.append(" -+");
        }
        parent.prefixForSub(sb);
        return sb.reverse().append(str).toString();
    }

    private void prefixForSub(StringBuilder sb) {
        if (parent == null) {
            return;
        }
        if (parent.childrenTail == this) {
            parent.prefixForSub(sb.append("   "));
        } else {
            parent.prefixForSub(sb.append("  |"));
        }
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getChildrenHead() {
        return childrenHead;
    }

    public void setChildrenHead(TreeNode childrenHead) {
        this.childrenHead = childrenHead;
    }

    public TreeNode getChildrenTail() {
        return childrenTail;
    }

    public void setChildrenTail(TreeNode childrenTail) {
        this.childrenTail = childrenTail;
    }

    public TreeNode getPrevious() {
        return previous;
    }

    public void setPrevious(TreeNode previous) {
        this.previous = previous;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }
}
