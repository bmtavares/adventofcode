import java.io.File

fun main() {
    val puzzle = File("puzzleinput.txt").readLines().map { it.toInt() }
    val result = findNumb(puzzle)

    println(result.first * result.second)
}

fun findNumb(list: List<Int>): Pair<Int,Int> {
    val targetResult = 2020

    (0 until list.size).forEach{ i ->
        (i until list.size).forEach { j ->
            if ((list[i] + list[j]) == targetResult)
                return Pair(list[i],list[j])
        }
    }

    return Pair(-1,-1)
}