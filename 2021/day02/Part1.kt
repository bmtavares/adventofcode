import java.io.File

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()
    var horizontalPos = 0
    var depth = 0

    puzzleLines.forEach{
        val command = it.split(' ')
        var units = command[1].toInt()

        when (command[0]) {
            "forward" -> horizontalPos += units
            "up" -> depth -= units
            "down" -> depth += units
        }
    }

    print(horizontalPos * depth)
}