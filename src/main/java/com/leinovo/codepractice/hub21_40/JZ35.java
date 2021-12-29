package com.leinovo.codepractice.hub21_40;

import com.leinovo.codepractice.dataStructure.RandomListNode;

import java.util.Stack;

/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
 * 请对此链表进行深拷贝，并返回拷贝后的头结点。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）。
 * 下图是一个含有5个结点的复杂链表。
 * 图中实线箭头表示next指针，虚线箭头表示random指针。为简单起见，指向null的指针没有画出。
 *
 * @description: JZ35
 * @author: leiming5
 * @date: 2021/11/18 16:32
 */
public class JZ35 {

    public static void main(String[] args) {

        RandomListNode rootA = new RandomListNode(1);
        RandomListNode rootB = new RandomListNode(2);
        RandomListNode rootC = new RandomListNode(3);
        RandomListNode rootD = new RandomListNode(4);
        RandomListNode rootE = new RandomListNode(5);

        rootA.next = rootB;
        rootB.next = rootC;
        rootC.next = rootD;
        rootD.next = rootE;

//        rootA.random = rootC;
//        rootB.random = rootE;
        rootD.random = rootB;

        RandomListNode result = Clone(rootA);
        System.out.println("xxxx");
    }

    /**
     * 1. 复制直线上的所有节点
     * 2. 遍历节点，找节点的 random , 匹配，匹配规则：它的下级 label 完全相等;
     * 3. 匹配到了random , 关联
     *
     * @param pHead
     * @return
     */
    public static RandomListNode Clone(RandomListNode pHead) {

        if (pHead == null) {
            return null;
        }

        // 1.
        RandomListNode copper = cloneLine(pHead);

        // 2.
        cloneRandom(pHead, copper,copper);

        return copper;
    }

    /**
     * 找到所有节点的 random 节点，并关联
     *
     * @param pHead
     * @param allSource
     */
    private static void cloneRandom(RandomListNode pHead, RandomListNode copper, RandomListNode allSource) {
        RandomListNode targetNode = pHead.random;
        if (targetNode != null) {
            RandomListNode foundTargetRandom = findRandom(targetNode, allSource);
            copper.random = foundTargetRandom;
        }

        if (pHead.next != null) {
            cloneRandom(pHead.next, copper.next, allSource);
        }
    }

    /**
     * 找到目标节点在所有节点中相等的节点；
     *
     * @param targetNode
     * @param copper
     * @return
     */
    private static RandomListNode findRandom(RandomListNode targetNode, RandomListNode copper) {

        Stack<RandomListNode> stack = new Stack<>();
        RandomListNode temp = copper;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0) {
            RandomListNode pop = stack.pop();
            if (sureRandom(pop, targetNode)) {
                return pop;
            }
        }

        return null;
    }

    /**
     * 确认两个节点是否相等
     *
     * @param originRandom
     * @param targetNode
     * @return
     */
    private static boolean sureRandom(RandomListNode originRandom, RandomListNode targetNode) {

        if (originRandom == null && targetNode == null) {
            return true;
        }
        if (originRandom != null && targetNode != null && originRandom.label == targetNode.label) {

            return sureRandom(originRandom.next, targetNode.next);
        }
        return false;
    }

    /**
     * 先复制整个节点，关联上下关系；
     *
     * @param pHead
     * @return
     */
    public static RandomListNode cloneLine(RandomListNode pHead) {

        RandomListNode copper = new RandomListNode(pHead.label);
        if (pHead.next != null) {
            copper.next = cloneLine(pHead.next);
        }
        return copper;
    }

}
