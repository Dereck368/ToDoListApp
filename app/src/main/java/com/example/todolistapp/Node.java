package com.example.todolistapp;

public class Node<E> {
    private E data;
    private Node<E> prev;
    private Node<E> next;

    public Node(E item) {
        data = item;
        prev = null;
        next = null;
    }

    public Node(E item, Node<E> next) {
        data = item;
        this.next = next;
        prev = null;
    }

    public Node(E item, Node<E> next, Node<E> prev) {
        data = item;
        this.next = next;
        this.prev = prev;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}

