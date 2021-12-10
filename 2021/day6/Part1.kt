import java.io.File

fun main() {
    val days = 80
    val parentAge = 0
    val recoveryAge = 6
    val babyAge = 8
    val puzzleLines = File("puzzleinput.txt").readLines()
    var fishies = puzzleLines[0].split(',').map { it.toInt() }

    (1..days).forEach {
        val parents = fishies.filter { it == parentAge }.size
        fishies = fishies.map { if (it == parentAge) recoveryAge else it - 1 }
        fishies += List(parents) { babyAge }
    }

    println(fishies.size)
}
