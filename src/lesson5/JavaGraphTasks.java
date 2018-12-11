package lesson5;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */

    // R = R(n) n - количество ребер
    // T = O(n)

    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        Set<Graph.Vertex> result = new HashSet<>();
        Set<Graph.Vertex> resultInvert = new HashSet<>();
        Set<Graph.Vertex> vertices = graph.getVertices();
        Set<Graph.Edge> connect = graph.getEdges();
        Graph.Edge start = null;

        for (Graph.Edge vertex : connect) {
            if (result.isEmpty() || vertex.getBegin() == start.getBegin()) start = vertex;
            if (!result.contains(start.getEnd())) {
                result.add(start.getEnd());
                resultInvert.add(start.getBegin());
            }
            if (result.contains(vertex.getBegin())) resultInvert.add(vertex.getEnd());
            if (resultInvert.contains(vertex.getBegin())) result.add(vertex.getEnd());
        }
        if (result.size() > resultInvert.size()) return result;
        else return resultInvert;
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */

    // R = R(n) n - количество вершин
    // T = O(n)

    public static Path longestSimplePath(Graph graph) {
        Set<Graph.Vertex> vertices = graph.getVertices();
        Path result = new Path(vertices.iterator().next());
        Set<Graph.Vertex> neighbors;
        Queue<Path> que = new ArrayDeque<>();

        for (Graph.Vertex vertex : vertices) {
            que.add(new Path(vertex));
        }

        while (!que.isEmpty()) {
            Path current = que.poll();
            List<Graph.Vertex> v = current.getVertices();
            if (current.getLength() > result.getLength()) {
                result = current;
                if (v.size() > vertices.size()) {
                    break;
                }
            }
            neighbors = graph.getNeighbors(v.get(v.size() - 1));

            for (Graph.Vertex neigh : neighbors) {
                if (!current.contains(neigh)) {
                    que.offer(new Path(current, graph, neigh));
                }
            }
        }

        return result;
    }
}
