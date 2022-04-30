package com.patni.lld.algorithms;

public class DLLNode<E> {
    DLLNode<E> next;
    DLLNode<E> prev;
    E element;

    public DLLNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public DLLNode<E> getNext() {
        return next;
    }

    public void setNext(DLLNode<E> next) {
        this.next = next;
    }

    public DLLNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DLLNode<E> prev) {
        this.prev = prev;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}