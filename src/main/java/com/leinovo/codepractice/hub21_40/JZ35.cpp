class JZ35 {
/**
*此解法参考了大佬的做法, 主要思路是将原链表的结点对应的拷贝节点连在其后, 最后链表变成 原1 -> 拷1 -> 原2 -> 拷2 -> ... -> null 的形式
 然后我们再逐步处理对应的随机指针, 使用双指针, 一个指针指向原链表的节点, 一个指向拷贝链表的节点, 那么就有 拷->random = 原->random->next (random不为空)
 最后再用双指针将两条链表拆分即可, 此算法大大优化了空间复杂度, 十分优秀
*
*/
public:
    RandomListNode* Clone(RandomListNode* pHead) {
        if(!pHead) return pHead;    // 为空则返回
        RandomListNode* cur = pHead;
        while(cur){
            RandomListNode* tmp = new RandomListNode(cur->label);    // 拷贝节点
            tmp->next = cur->next;
            cur->next = tmp;
            cur = tmp->next;
        }

        RandomListNode *old = pHead, *clone = pHead->next, *ret = pHead->next;
        while(old){
            clone->random = old->random == NULL ? NULL : old->random->next;    // 处理拷贝节点的随机指针
            if(old->next) old = old->next->next;    // 注意特判空指针
            if(clone->next) clone = clone->next->next;
        }

        old = pHead, clone = pHead->next;
        while(old){    // 拆分链表
            if(old->next) old->next = old->next->next;
            if(clone->next) clone->next = clone->next->next;
            old = old->next;
            clone = clone->next;
        }

        return ret;
    }
};