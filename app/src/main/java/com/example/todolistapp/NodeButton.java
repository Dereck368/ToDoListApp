package com.example.todolistapp;

import android.content.Context;
import android.widget.Button;

/**
 * this is button that holds a node
 * @param <E>
 */
public class NodeButton<E> extends androidx.appcompat.widget.AppCompatButton {
    private Node<E> node;

    public NodeButton(Context context, Node<E> node) {
        super(context);
        this.node = node;
    }

    public Node<E> getNode(Node<E> node) {
        return node;
    }
}
