package model.robot;

import model.graph.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Alexandre
 *         27/05/2015
 */
public class NodePath implements Iterator<Node> {
    private Boolean accepted = Boolean.FALSE;

    private LinkedList<Node> path = new LinkedList<>();

    public NodePath(List<Node> path) {
        this.path.addAll(path);
    }

    public NodePath() {
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return !path.isEmpty() && accepted;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Node next() {
        if (!hasNext()) throw new NoSuchElementException();
        return path.poll();
    }

    public void accept() {
        accepted = Boolean.TRUE;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void addLast(Node n) {
        path.addLast(n);
    }

    public void addFirst(Node n) {
        path.addFirst(n);
    }

    public Node getDestination() {
        return path.isEmpty() ? null : path.getLast();
    }
}
