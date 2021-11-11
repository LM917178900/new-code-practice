package com.leinovo.codepractice.hub1_20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.max;

/**
 * 描述
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长的 m 段（ m 、 n 都是整数， n > 1 并且 m > 1 ， m <= n ），每段绳子的长度记为 k[1],...,k[m] 。请问 k[1]*k[2]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到的最大乘积是 18 。
 * <p>
 * 数据范围： 2≤n≤60
 * 进阶：空间复杂度 O(1) ，时间复杂度 O(n)
 * 输入描述：
 * 输入一个数n，意义见题面。
 *
 * @description: Solution14
 * @author: leiming5
 * @date: 2021/10/25 14:56
 */
public class Solution14 {

    public static void main(String[] args) {
        int result = cutRope(15);
        System.out.println(result);
    }
    public static int cutRope(int target) {
        if (target == 2 || target == 3)
            return target - 1;
        int res = 1;
        while (target > 4) {
            //如果target大于4，我们不停的让他减去3
            target = target - 3;
            //计算每段的乘积
            res = res * 3;
        }
        return target * res;
    }

    public   static   int cutRopeyy(int number) {
        if (number == 2) {
            return 1;
        }
        else if (number == 3) {
            return 2;
        }
        Map<Integer,Integer> map = new HashMap<>(32);
        for (int i = 1; i <= 4; ++i) {
            map.put(i,i);
        }
        for (int i = 5; i <= number; ++i) {
            for (int j = 1; j < i; ++j) {
                map.put(i,max(map.get(i), j * map.get(i - j)));
            }
        }
        return map.get(number);
    }

    public static int cutRopexxx(int target) {
        if (target == 2) {
            return 1;
        }
        else if (target == 3) {
            return 2;
        }
        Map<Integer,Integer> mark = new HashMap<>(32);
        return back_track(target,mark);
    }

    /**
     * 对于长度n，我们需要减少递归参数n，如果第一段为1，
     * 显然下一步递归为back_track(n-1),如果第一段为2，
     * 则下一步递归为
     * back_track(n-2)...因为要至少分2段，
     * 所以，最后一次可能的情况为最后一段为n-1, 下一步递归为back_track(1)，
     * 因此，每一步可能的结果为
     * 1 * back_track(n-1), 2 * back_track(n-2), ..., (n-1) * back_track(1),在n-1种情况中取一个最大值即可。
     * 这里我们不用关系back_track(n-1)等的值为多少，因为最终会递归到我们的终止条件，因此绝对是可以求出来。
     *
     * @param n
     * @return
     */
    public static int back_track(int n,Map<Integer,Integer> mark ) {
        // n <= 4, 表明不分，长度是最大的
        if (n <= 4) {
            return n;
        }
        if(mark.get(n)!=null){
            return mark.get(n);
        }

        int ret = 0;
        for (int i = 1; i < n; ++i) {
            int next = back_track(n - i,mark);
            ret = max(ret, i * next );
        }

        mark.put(n,ret);
        return ret;
    }
    public static int cutRopeback(int target) {

        if (target <= 4) {
            return target;
        }
        if (target == 5) {
            return 6;
        }
        if (target == 6) {
            return 9;
        }

        int twoSize = 0;
        Integer b1 = cutRope(target / 2);
        Integer b2 = cutRope(target / 2 + 1);
        if (target % 2 == 1) {
            twoSize = b1 * b2;
        } else {
            twoSize = b1 * b1;
        }

        int treeSize = 0;
        Integer c1 = cutRope(target / 3);
        Integer c2 = cutRope(target / 3 + 1);
        if (target % 3 == 1) {
            treeSize = c1 * c1 * c2;
        } else if (target % 3 == 2) {
            treeSize = c1 * c2 * c2;
        } else {
            treeSize = c1 * c1 * c1;
        }

        int fiveSize = 0;
        int f1 = cutRope(target / 5);
        int f2 = cutRope(target / 5 + 1);
        if (target % 5 == 1) {
            fiveSize = f1 * f1 * f1 * f1 * f2;
        } else if (target % 5 == 2) {
            fiveSize = f1 * f1 * f1 * f2 * f2;
        } else if (target % 5 == 3) {
            fiveSize = f1 * f1 * f2 * f2 * f2;
        } else if (target % 5 == 4) {
            fiveSize = f1 * f2 * f2 * f2 * f2;
        } else {
            fiveSize = f1 * f1 * f1 * f1 * f1;
        }

        int bigger = treeSize > twoSize ? treeSize : twoSize;

        return bigger > fiveSize ? bigger : fiveSize;
    }


}
