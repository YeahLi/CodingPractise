package com.basics.graph.tree.binarytree.binaryheap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MyBinaryHeap {
    private List<Integer> heap;

    public MyBinaryHeap(int[] arr) {
        build(arr);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void offer(int data) {
        heap.add(data);
        upAdjust();
    }

    public int poll() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Heap is empty now.");
        }
        int result = heap.get(0);
        //把最后一个节点放到 root 上
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        //向下调整
        downAdjust(0);
        return result;
    }

    public void build(int[] completeTree) {
        heap = Arrays.stream(completeTree).boxed().collect(Collectors.toList());
        //从最后一个非叶子节点开始依次做下沉
        for (int i = (heap.size() - 2) / 2; i >= 0; i--) {
            downAdjust(i);
        }
    }

    private void upAdjust() {
        int childIndex = heap.size() - 1;
        int parentIndex = (childIndex - 1) / 2;

        while (childIndex > 0 && heap.get(childIndex) < heap.get(parentIndex)) {
            int temp = heap.get(childIndex);
            heap.set(childIndex, heap.get(parentIndex));
            heap.set(parentIndex, temp);
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
    }

    private void downAdjust(int index) {
        while (index < heap.size()) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            int childIndex = -1;
            if (rightChildIndex < heap.size()) {
                childIndex = heap.get(leftChildIndex) < heap.get(rightChildIndex) ? leftChildIndex : rightChildIndex;
            } else if (leftChildIndex < heap.size() && rightChildIndex >= heap.size()) {
                childIndex = leftChildIndex;
            } else {
                break;
            }

            if (heap.get(childIndex) < heap.get(index)) {
                int temp = heap.get(childIndex);
                heap.set(childIndex, heap.get(index));
                heap.set(index, temp);
                index = childIndex;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] completeTree = {5, 3, 4, 1, 2};
        MyBinaryHeap binaryHeap = new MyBinaryHeap(completeTree);
        List<Integer> list = new LinkedList<>();
        while (!binaryHeap.isEmpty()) {
            list.add(binaryHeap.poll());
        }
        System.out.println(list);
    }

}
