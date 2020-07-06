package org.springboot.leetcode;

import lombok.Data;

/**
 * @ClassName Node
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/5 10:57
 * @Version 1.0
 */
@Data
public class Node {
    Node next = null;// 节点的引用，指向下一个节点
    Integer data;// 节点的对象，即内容
    public Node(Integer data) {
        this.data = data;
    }
    public Node(Integer data,Node node) {
        this.data = data;
        this.next = node;
    }
}