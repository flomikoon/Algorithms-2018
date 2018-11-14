package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */

    private Node<T> remove(Node<T> tree, Object o) {
        @SuppressWarnings("unchecked")
        T key = (T) o;
        if (tree == null) {
            return null;
        }
        int comparator = key.compareTo(tree.value);
        if (comparator < 0)
            tree.left = remove(tree.left, o);
        else if (comparator > 0)
            tree.right = remove(tree.right, o);
        else {
            if (tree.left != null && tree.right != null) {
                Node<T> cloneTree = tree.right;
                while (cloneTree.left != null) {
                    cloneTree = cloneTree.left;
                }
                tree.value = cloneTree.value;
                tree.right = remove(tree.right, tree.value);
            } else if (tree.left != null) {
                return tree.left;
            } else {
                return tree.right;
            }
        }
        return tree;
    }

    // R = R(n)
    // T = O(height)
    @Override
    public boolean remove(Object o) {
        root = remove(root, o);
        size--;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Node<T> next;

        private Stack<Node<T>> stackNode = new Stack<>();

        private BinaryTreeIterator() {
            next = root;
            while (next != null) {
                stackNode.push(next);
                next = next.left;
            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            next = stackNode.pop();
            Node<T> stackUpdate = next;
            if (stackUpdate.right != null) {
                stackUpdate = stackUpdate.right;
                while (stackUpdate != null) {
                    stackNode.push(stackUpdate);
                    stackUpdate = stackUpdate.left;
                }
            }
            return next;
        }
        // R = R(n)
        // T = O(n)

        @Override
        public boolean hasNext() {
            return !stackNode.empty();
        }

        @Override
        public T next() {
            next = findNext();
            if (next == null) throw new NoSuchElementException();
            return next.value;
        }


        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            T value = next.value;

            if (!hasNext()) {
                BinaryTree.this.remove(value);
                next = find(value);
            } else {
                BinaryTree.this.remove(value);
                next();
            }
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        SortedSet<T> result = new BinaryTree<>();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(toElement) == 0) {
                result.add(value);
                break;
            }
            if (value.compareTo(fromElement) >= 0) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        SortedSet<T> result = new BinaryTree<>();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(toElement) < 0) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        BinaryTreeIterator iterator = new BinaryTreeIterator();
        SortedSet<T> result = new BinaryTree<>();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value.compareTo(fromElement) >= 0) {
                result.add(value);
            }
        }
        return result;
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}