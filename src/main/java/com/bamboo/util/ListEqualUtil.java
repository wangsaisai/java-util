package com.bamboo.util;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListEqualUtil {

    /**
     * 比较两个list是否相同，忽略顺序，忽略重复元素
     * @param list1
     * @param list2
     * @param <E>
     * @return
     */
    public static <E> boolean equalIgnoreOrderAndDuplicate(List<E> list1, List<E> list2) {
        if (list1 == null && list2 == null) {
            return true;
        } else if (list1 == null || list2 == null) {
            return false;
        }

        Set<E> set1 = new HashSet<>(list1);
        Set<E> set2 = new HashSet<>(list2);
        return Sets.difference(set1, set2).isEmpty();
    }

}
