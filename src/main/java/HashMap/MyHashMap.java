package HashMap;

import java.util.*;

/**
 * 手撕HashMap
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K,V> {

    /**
     * 节点类
     * @param <K>
     * @param <V>
     */
    class Node<K, V> {
        /**
         * 键值对
         */
        private K key;
        private V value;

        /**
         * 链表，后继
         */
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 默认容量
     */
    final int DEFAULT_CAPACITY = 16;

    /**
     * 负载因子
     */
    final float LOAD_FACTOR = 0.75f;

    /**
     * HashMap的大小
     */
    private int size;


    /**
     * 桶数组
     */
    Node<K,V>[] buckets;


    /**
     * 无参构造器，设置桶数组默认容量
     */
    public MyHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * 有参构造器，指定桶数组容量
     *
     * @param capacity
     */
    public MyHashMap(int capacity) {
        buckets = new Node[capacity];
        size = 0;
    }



    /**
     * 哈希函数，获取地址
     *
     * @param key
     * @return
     */
    private int getIndex(K key, int length) {
        //获取hash code
        int hashCode = key.hashCode();

        //和桶数组长度取余
        int index = hashCode % length;
        return Math.abs(index);
    }


    /**
     * put方法
     *
     * @param key
     * @param value
     * @return
     */
    public void put(K key, V value) {
        //判断是否需要进行扩容
        if (size >= buckets.length * LOAD_FACTOR){
            resize();
        }
        putVal(key, value, buckets);
    }

    /**
     * 将元素存入指定的node数组
     *
     * @param key
     * @param value
     * @param table
     */
    private void putVal(K key, V value, Node<K, V>[] table) {
        //获取位置
        int index = getIndex(key, table.length);
        Node node = table[index];
        //插入的位置为空
        if (node == null) {
            table[index] = new Node<>(key, value);
            size++;
            return;
        }
        //插入位置不为空，说明发生冲突，使用链地址法,遍历链表
        while (node != null) {
            //如果key相同，就覆盖掉
            if ((node.key.hashCode() == key.hashCode())
                    && (node.key == key || node.key.equals(key))) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        //当前key不在链表中，插入链表头部
        Node newNode = new Node(key, value, table[index]);
        table[index] = newNode;
        size++;
    }

    /**
     * 扩容
     */
    private void resize() {
        //创建一个两倍容量的桶数组
        Node<K, V>[] newBuckets = new Node[buckets.length * 2];
        //将当前元素重新散列到新的桶数组
        rehash(newBuckets);
        buckets = newBuckets;
    }

    /**
     * 重新散列当前元素
     *
     * @param newBuckets
     */
    private void rehash(Node<K, V>[] newBuckets) {
        //map大小重新计算
        size = 0;
        //将旧的桶数组的元素全部刷到新的桶数组里
        for (int i = 0; i < buckets.length; i++) {
            //为空，跳过
            if (buckets[i] == null) {
                continue;
            }
            Node<K, V> node = buckets[i];
            while (node != null) {
                //将元素放入新数组
                putVal(node.key, node.value, newBuckets);
                node = node.next;
            }
        }
    }


    /**
     * 获取元素
     *
     * @param key
     * @return
     */
    public V get(K key) {
        //获取key对应的地址
        int index = getIndex(key, buckets.length);
        if (buckets[index] == null) {
            return null;
        }
        Node<K, V> node = buckets[index];
        //查找链表
        while (node != null) {
            if ((node.key.hashCode() == key.hashCode())
                    && (node.key == key || node.key.equals(key))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
}
