import java.io.File

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()

    var totalSum = 0
    (0 until puzzleLines.size).forEach{
        if (it != 0){
            val firstEl = puzzleLines[it-1].toInt()
            val secondEl = puzzleLines[it].toInt()
            if(firstEl < secondEl)
                totalSum++
        }
    }

    print(totalSum)
}