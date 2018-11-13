package lesson3

import java.util.SortedSet
import kotlin.test.*

abstract class AbstractHeadTailTest {
    private lateinit var tree: SortedSet<Int>
    private lateinit var tree1: SortedSet<Int>

    protected fun fillTree(empty: SortedSet<Int>) {
        this.tree = empty
        //В произвольном порядке добавим числа от 1 до 10
        tree.add(1000)
        tree.add(5)
        tree.add(1)
        tree.add(2)
        tree.add(89)
        tree.add(7)
        tree.add(9)
        tree.add(500)
        tree.add(10)
        tree.add(8)
        tree.add(4)
        tree.add(724)
        tree.add(3)
        tree.add(6)
        tree.add(621)
    }

    protected fun fillTree1(empty: SortedSet<Int>) {
        this.tree1 = empty
    }

    protected fun doHeadSetTest() {
        var set: SortedSet<Int> = tree.headSet(5)
        assertEquals(true, set.contains(1))
        assertEquals(true, set.contains(2))
        assertEquals(true, set.contains(3))
        assertEquals(true, set.contains(4))
        assertEquals(false, set.contains(5))
        assertEquals(false, set.contains(6))
        assertEquals(false, set.contains(7))
        assertEquals(false, set.contains(8))
        assertEquals(false, set.contains(9))
        assertEquals(false, set.contains(10))


        val set1: SortedSet<Int> = tree.headSet(0)
        assertEquals(false, set1.contains(1))
        assertEquals(false, set1.contains(2))
        assertEquals(false, set1.contains(3))
        assertEquals(false, set1.contains(4))
        assertEquals(false, set1.contains(5))
        assertEquals(false, set1.contains(6))
        assertEquals(false, set1.contains(7))
        assertEquals(false, set1.contains(8))
        assertEquals(false, set1.contains(9))
        assertEquals(false, set1.contains(10))
        assertEquals(false, set1.contains(1000))

        set = tree.headSet(127)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

        val set2: SortedSet<Int> = tree1.headSet(1200)
        assertEquals(false, set2.contains(1000))

    }

    protected fun doTailSetTest() {
        var set: SortedSet<Int> = tree.tailSet(5)
        assertEquals(false, set.contains(1))
        assertEquals(false, set.contains(2))
        assertEquals(false, set.contains(3))
        assertEquals(false, set.contains(4))
        assertEquals(true, set.contains(5))
        assertEquals(true, set.contains(6))
        assertEquals(true, set.contains(7))
        assertEquals(true, set.contains(8))
        assertEquals(true, set.contains(9))
        assertEquals(true, set.contains(10))

        set = tree.tailSet(-128)
        for (i in 1..10)
            assertEquals(true, set.contains(i))

        val set1: SortedSet<Int> = tree.tailSet(128)
        assertEquals(false, set1.contains(1))
        assertEquals(false, set1.contains(2))
        assertEquals(false, set1.contains(3))
        assertEquals(false, set1.contains(4))
        assertEquals(true, set1.contains(500))
        assertEquals(true, set1.contains(621))
        assertEquals(true, set1.contains(724))
        assertEquals(false, set1.contains(89))
        assertEquals(false, set1.contains(9))
        assertEquals(true, set1.contains(1000))

        val set2: SortedSet<Int> = tree1.tailSet(1200)
        assertEquals(false, set2.contains(1000))
    }

    protected fun doHeadSetRelationTest() {
        val set: SortedSet<Int> = tree.headSet(7)
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
        tree.add(0)
        assertTrue(set.contains(0))
        set.remove(4)
        assertFalse(tree.contains(4))
        tree.remove(6)
        assertFalse(set.contains(6))
        tree.add(12)
        assertFalse(set.contains(12))
        assertEquals(5, set.size)
        assertEquals(10, tree.size)
    }

    protected fun doTailSetRelationTest() {
        val set: SortedSet<Int> = tree.tailSet(4)
        assertEquals(7, set.size)
        assertEquals(10, tree.size)
        tree.add(12)
        assertTrue(set.contains(12))
        set.remove(4)
        assertFalse(tree.contains(4))
        tree.remove(6)
        assertFalse(set.contains(6))
        tree.add(0)
        assertFalse(set.contains(0))
        assertEquals(6, set.size)
        assertEquals(10, tree.size)
    }

    protected fun doSubSetTest() {
        var set: SortedSet<Int> = tree.subSet(5, 8)
        assertEquals(false, set.contains(1))
        assertEquals(false, set.contains(2))
        assertEquals(false, set.contains(3))
        assertEquals(false, set.contains(4))
        assertEquals(true, set.contains(5))
        assertEquals(true, set.contains(6))
        assertEquals(true, set.contains(7))
        assertEquals(true, set.contains(8))
        assertEquals(false, set.contains(9))
        assertEquals(false, set.contains(10))

        set = tree.subSet(-3, 21)
        for (i in 1..10)
            assertEquals(true, set.contains(i))
    }

}