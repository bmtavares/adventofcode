import java.io.File

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()
    var gammabits = ""
    var epsilonbits = ""

    (0..puzzleLines[0].length-1).forEach{ currentColumnIdx ->
        val count = puzzleLines.fold(0){ accum, currentLine ->
            accum + currentLine[currentColumnIdx].digitToInt()
        }

        val isOneMostCommon = count > puzzleLines.size / 2
        gammabits += if (isOneMostCommon) '1' else '0'
        epsilonbits += if (isOneMostCommon) '0' else '1'
    }


    val gammarate = gammabits.toInt(2)
    val epsilonrate = epsilonbits.toInt(2)

    print(gammarate*epsilonrate)
}