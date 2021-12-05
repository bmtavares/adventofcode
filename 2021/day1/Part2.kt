import java.io.File

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()
    val windowSize = 3
    val puzzleInts = puzzleLines.map {
        it.toInt()
    }

    var totalSum = 0

    (0..puzzleInts.size-windowSize).forEach{
        if (it != 0) {
            val firstSum = puzzleInts.slice(it-1..it+windowSize-2)
            val secondSum = puzzleInts.slice(it..it+windowSize-1)

            if(firstSum.sum() < secondSum.sum())
                totalSum++
        }
    }

    print(totalSum)
}