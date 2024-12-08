import java.io.File

fun main() {
    val puzzle = File("puzzleinput.txt").readLines().map { it.toInt() }
    val result = findNumb(puzzle)

    println(result.first * result.second * result.third)
}

fun findNumb(list: List<Int>): Triple<Int,Int,Int> {
    val targetResult = 2020

    (0 until list.size).forEach{ i ->
        (i until list.size).forEach { j ->
            (j until list.size).forEach { k ->
                if ((list[i] + list[j] + list[k]) == targetResult)
                return Triple(list[i],list[j],list[k])
            }
        }
    }

    return Triple(-1,-1,-1)
}