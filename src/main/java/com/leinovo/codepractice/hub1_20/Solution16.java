package com.leinovo.codepractice.hub1_20;

import com.leinovo.codepractice.dataStructure.ListNode;

/**
 * JZ16 数值的整数次方
 *
 * @description: Solution16
 * @author: leiming5
 * @date: 2021/10/9 9:02
 */
public class Solution16 {
    public static void main(String[] args) {
        double power = Power(2, -3);
        System.out.println(power);
    }

    /**
     * 实现函数 double Power(double base, int exponent)，求base的exponent次方。
     * 1.保证base和exponent不同时为0。
     * 2.不得使用库函数，同时不需要考虑大数问题
     * 3.有特殊判题，不用考虑小数点后面0的位数。
     * <p>
     * 数据范围:
     * -100<base<100
     * -100<exponent<100
     * -104<函数返回的结果<104
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double Power(double base, int exponent) {
        boolean isNegative = exponent < 0;
        if (isNegative) {
            exponent = -exponent;
        }

        double result = q_power(base, exponent);
        return isNegative ? 1 / result : result;
    }

    /**
     * 偶数 分开计算
     * 奇数也分开计算
     */
    public static double q_power(double b, int n) {
        if (n == 0) {
            return 1;
        }
        double temp = q_power(b, n / 2);
        double result = temp * temp;

        if ((n & 1) == 1) {
            return result * b;
        }
        return result;
    }


    public static double PowerBack(double base, int exponent) {

        boolean isNegative = exponent < 0;
        if (isNegative) {
            exponent = -exponent;
        }

        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return isNegative ? 1 / result : result;
    }

    public static ListNode Merge(ListNode list1, ListNode list2) {

        ListNode result;

        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        // handle B
        if (list1.val > list2.val) {
            result = new ListNode(list2.val);
            result.next = Merge(list1, list2.next);
        } else {
            result = new ListNode(list1.val);
            result.next = Merge(list1.next, list2);
        }

        return result;
    }


    public static ListNode MergeBack(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        if (list2.val > list1.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }


    /**
     * 1.生成新节点
     * 2.赋值新节点
     * 3.节点B 遍历
     * 4.递归后面的节点
     *
     * @param tempA
     * @param tempB
     */
    public static ListNode getCurrentNode(ListNode tempA, ListNode tempB) {

        ListNode middle;

        if (tempA == null) {
            return tempB;
        } else if (tempB == null) {
            return tempA;
        }

        // handle B
        if (tempA.val > tempB.val) {
            middle = new ListNode(tempB.val);
            middle.next = getCurrentNode(tempA, tempB.next);
        } else {
            middle = new ListNode(tempA.val);
            middle.next = getCurrentNode(tempA.next, tempB);
        }
        return middle;
    }


    public static ListNode MergeCross(ListNode list1, ListNode list2) {

        // 生成新的节点
        ListNode result = null;
        // 遍历A/B 节点
        ListNode tempA = list1;
        ListNode tempB = list2;
        ListNode middle = null;

        int i = 0;
        while (tempA != null) {
            // 1. A 生成当前节点1.1
            // 2. A 节点1.1 设置val;
            ListNode newA = new ListNode(tempA.val);

            // 3. B 生成当前节点 1.2
            // 4. B 节点1.2 设置val
            ListNode newB = null;
            if (tempB != null) {
                newB = new ListNode(tempB.val);
            }
            // 5. 绑定1.1/1.2 节点，生成1.1 节点
            // 6. 绑定之前的节点/1.1 节点
            if (i == 0) {
                result = newA;
            } else {
                middle.next = newA;
            }
            newA.next = newB;
            if (newB == null) {
                middle = newA;
            } else {
                middle = newB;
            }

            // 7. A/B 遍历；
            tempA = tempA.next;
            if (tempB != null) {
                tempB = tempB.next;
            }

            i++;
        }
        return result;
    }
}
