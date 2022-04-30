package com.patni.lld.algorithms;

import com.patni.lld.algorithms.Exception.InvalidElementException;

import java.util.NoSuchElementException;

public class DLL<E> {

    DLLNode<E> dummyHead;
    DLLNode<E> dummyTail;

    public DLL() {
        dummyHead = new DLLNode<>(null);
        dummyTail = new DLLNode<>(null);

        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void detachNode(DLLNode<E> node) {
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtLast(DLLNode<E> node) {
        DLLNode tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }

    public DLLNode<E> addElementAtLast(E element) {
        if (element == null) {
            throw new InvalidElementException();
        }
        DLLNode<E> newNode = new DLLNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemPresent() {
        return dummyHead.next != dummyTail;
    }

    public DLLNode getFirstNode() throws NoSuchElementException {
        DLLNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyHead.next;
    }

    public DLLNode getLastNode() throws NoSuchElementException {
        DLLNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyTail.prev;
    }
}