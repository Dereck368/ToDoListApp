package com.example.todolistapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/*
 * Using this to make a mobile dev app
 * uses a kind of queue data structure but
 * will never have dequeue because for the
 * mobile app we want user to be able to delete any
 * item they want
 */
public class ToDoList /*implements Parcelable*/ {
    private final int size;
    private int capacity;
    private Node<String> head;
    private Node<String> tail;

    // todo need a get method for name, also need to set up id.
    private final String name;

    public ToDoList(String name) {
        size = 10;
        capacity = 0;
        head = null;
        tail = null;
        this.name = name;
    }

    //add item to todolist
    public Node<String> addToList(String item) {
        if (capacity < size) {
            if (head == null) {
                Node<String> n = new Node<String>(item);
                head = n;
                tail = n;
                capacity += 1;
                return n;
            } else {
                Node<String> n = new Node<String>(item, null);
                tail.setNext(n);
                n.setPrev(tail);
                tail = n;
                capacity += 1;
                return n;
            }
        } else {
            return new Node<>(); //"list is full: delete item before adding new one"
        }
    }

    // delete item from todolist
    public void deleteItem(Node<String> item) {
        if (capacity > 0) {
            // removing node that both tail and head are on
            if (item == head && item == tail) {
                head = null;
                tail = null;
                capacity -= 1;
                return;
            }

            // remove head
            if (item == head) {
                head = item.getNext();
                head.setPrev(null);
                capacity -= 1;
                return;
            }

            // remove tail
            if (item == tail) {
                tail = item.getPrev();
                tail.setNext(null);
                capacity -= 1;
                return;
            }

            // remove middle item
            Node<String> prev = item.getPrev();
            Node<String> next = item.getNext();

            prev.setNext(next);
            next.setPrev(prev);
            capacity -= 1;
        } else {
            System.out.println("no items to delete");
        }
    }

    // rename item
    public void renameItem(Node<String> item, String name) {
        item.setData(name);
    }

    public String getName() {
        return name;
    }

    public Node<String> getHead() {
        return head;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append("[ ");

        Node<String> curr = head;
        while(curr != null) {
            build.append(curr.toString());
            if(curr.getNext() != null) {
                build.append(",");
            }
            build.append(" ");
            curr = curr.getNext();
        }

        build.append("]");

        return build.toString();
    }

    // thought I would need it to develop activity transfer,
    // decide to just send name, and have list activity have its
    // own sp for the list, which can be accesed by name

    /*
    // Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    // write linked list data to the passed-in Parcel
    // flattening list
    // so each value of list ex: [1, 2, 3, 4]
    // so then I can rebuild double linked list
    // 1 -> 2 -> 3 -> 4
    @Override
    public void writeToParcel(Parcel out, int flags) {
        List<String> nodeValues = new ArrayList<>();
        Node<String> curr = head;
        while(curr != null) {
            nodeValues.add(curr.toString());
            curr = curr.getNext();
        }
        out.writeStringList(nodeValues);
        out.writeInt(size);
        out.writeInt(capacity);
        out.writeString(name);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ToDoList> CREATOR = new Parcelable.Creator<ToDoList>() {
        public ToDoList createFromParcel(Parcel in) {
            return new ToDoList(in);
        }

        public ToDoList[] newArray(int size) {
            return new ToDoList[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    // reasoning I do this is because while researching how to transfer my class info
    // someone said parcelable was faster then serialization, so here we are.
    private ToDoList(Parcel in) {
        List<String> nodeValues = in.createStringArrayList();
        if (nodeValues != null && !nodeValues.isEmpty()) {
            head = new Node<>(nodeValues.get(0));
            Node<String> curr = head;
            for(int i = 1; i < nodeValues.size(); i++) {
                Node<String> newNode = new Node<>(nodeValues.get(i));
                curr.setNext(newNode);
                newNode.setPrev(curr);
                curr = newNode;
            }
        }
        size = in.readInt();
        capacity = in.readInt();
        name = in.readString();
    }
     */

}

