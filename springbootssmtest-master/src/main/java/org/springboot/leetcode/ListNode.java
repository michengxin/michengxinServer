package org.springboot.leetcode;

import lombok.Data;

/**
 * @ClassName ListNode
 * @Description TODO
 * @Author mcx
 * @Date 2020/6/5 10:04
 * @Version 1.0
 */
@Data
public class ListNode {
    private Node head; // 头节点
    private int size;//链表大小，即节点数量
    public ListNode(int val){
        this.head = new Node(val);
    }
    /**
     * 链表中的节点，data代表节点的值，next是指向下一个节点的引用
     *
     * @author mcx
     *
     */

    /**
     * 向链表中插入数据(头部)
     *
     * @param p
     */
    public void addNode(Integer p){
        if(head!=null){//如果有头节点，添加新的头节点，取代之前头节点(头插法)
            head = new Node(p,head);
        }
        else{//如果没有头节点，直接添加一个新节点
            head = new Node(p);
        }
        size ++;
    }


    /**
     * 向链表中插入数据(第index位置插入)
     *
     * @param p,
     */
    public void addNodeIndex(Integer p,int index) {
        if (index == 0) {//如果从0位置插入，即从头部插入
            addNode(p);
            size ++;
        } else {
            if(index>size){//index位置超出链表容量
                System.out.println("超出链表最大的节点数:"+size+",请重新输入要插入的索引位置。");
            }
            else{
                int count=0;
                for(Node pcn=head;pcn!=null;pcn=pcn.getNext()){//遍历链表
                    count++;
                    if(count==index){//找到index位置的节点，这时候的pcn是这个节点的前一个节点
                        Node pcn1 = new Node(p,pcn.getNext());//新节点pcn1的下一个节点是pcn节点的下一个节点
                        pcn.setNext(pcn1);//pcn的下一个节点变成新的节点pcn1
                    }
                }
                size ++;
            }
        }
    }
    /**
     * 向链表中删除指定数据(第index位置插入)
     *
     * @param index
     */
    public void deleteNode(int index) {
        if (index > size - 1) {//超出链表容量
            System.out.println("超出链表最大的节点数:" + size + ",请重新输入要删除的索引位置。");
        } else {
            if (index == 0) {//删除头节点的话
                head = head.getNext();//直接把头节点的下一个节点变成头节点
            } else {
                if (index == size - 1) {//如果是删除最后一个节点
                    int count = 0;
                    for (Node pcn = head; pcn != null; pcn = pcn.getNext()) {
                        count++;
                        if (count == index) {
                            pcn.setNext(null);//将倒数第二个节点，的下一个节点设置为空
                        }
                    }
                } else {//删除中间节点
                    int count = 0;
                    for (Node pcn = head; pcn != null; pcn = pcn.getNext()) {
                        count++;
                        if (count == index) {
                            pcn.setNext(pcn.getNext().getNext());
                            // pcn.getNextNode().setNextNode(null);
                        }
                    }
                }
            }
            size = size - 1;
        }
    }
}