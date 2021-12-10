import java.io.File

fun main() {
    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
    val puzzleLines = File("puzzleinput.txt").readLines()
    val lines =
            puzzleLines
                    .map { it.split(" -> ".toRegex()).map { it.split(',').map { it.toInt() } } }
                    .map { Line(it[0][0], it[0][1], it[1][0], it[1][1]) }
    var seaMap = hashMapOf<Pair<Int, Int>, Int>()

    lines.forEach { line ->
        if (line.x1 == line.x2) { // Horizontal
            val range = if (line.y1 < line.y2) (line.y1..line.y2) else (line.y2..line.y1)
            range.forEach { seaMap.addPair(Pair(line.x1, it)) }
        } else if (line.y1 == line.y2) { // Vertical
            val range = if (line.x1 < line.x2) (line.x1..line.x2) else (line.x2..line.x1)
            range.forEach { seaMap.addPair(Pair(it, line.y1)) }
        }
        else { // 45º Diagonal
            val difference = Math.abs(line.x1 - line.x2)
            val xDirection = if (line.x1 > line.x2) -1 else 1
            val yDirection = if (line.y1 > line.y2) -1 else 1
            (0..difference).forEach { seaMap.addPair(Pair(line.x1 + it * xDirection, line.y1 + it * yDirection)) }
        }
    }

    println(seaMap.filterValues { it > 1 }.size)
}

fun HashMap<Pair<Int, Int>, Int>.addPair(key: Pair<Int, Int>) {
    if (this.containsKey(key)) this.put(key, this.get(key)!! + 1) else this.put(key, 1)
}