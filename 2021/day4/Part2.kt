import java.io.File

val BINGO_SIZE = 5

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()
    val calledNumbers = puzzleLines[0].split(',').map { it.toInt() }

    var boards =
            puzzleLines
                    .slice(2..puzzleLines.size - 1)
                    .filter { !it.isNullOrEmpty() }
                    .mapNotNull { it.split(' ').filter { it.isNotEmpty() }.map { it.toInt() } }
                    .windowed(BINGO_SIZE, BINGO_SIZE)

    run fe@{
        calledNumbers.forEach { calledNum ->
            var newBoards = emptyList<List<List<Int>>>().toMutableList()
            boards.forEach {
                    val b =
                            searchBingoBoard(
                                    it.map { it.map { if (it == calledNum) -1 else it } }
                            )
                    if (boards.size == 1 && b.size == 0) {
                        println(
                                boards[0]
                                        .map {
                                            it
                                                    .map { if (it == calledNum) -1 else it }
                                                    .mapNotNull { if (it != -1) it else null }
                                                    .sum()
                                        }
                                        .sum() * calledNum
                        )
                        return@fe
                    }
                    if (b.size != 0) newBoards += b
            }
 
            boards = newBoards.toList()
        }
    }
}

fun searchBingoBoard(board: List<List<Int>>): List<List<Int>> {
    if (board.map { it.sum() == -BINGO_SIZE }.any { it }) {
        return emptyList<List<Int>>()
    }

    (0 until BINGO_SIZE).forEach { col ->
        if (board.map { it[col] }.sum() == -BINGO_SIZE) {
            return emptyList<List<Int>>()
        }
    }

    return board
}