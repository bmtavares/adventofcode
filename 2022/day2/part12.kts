import java.io.File

enum class Shape(val score:Int){
    Rock(1), Paper(2), Scissors(3);
    companion object {
        operator fun invoke(c:Char) =
            when(c) {
                'A','X' -> Rock
                'B','Y' -> Paper
                'C','Z' -> Scissors
                else -> null
            }
    }
}

fun Shape.score(opponent:Shape) = this.score + when (opponent) {
    this -> 3
    this.win() -> 6
    else -> 0
}

fun Shape.decision(opponent: Shape) = when (this) {
    Shape.Rock -> opponent.win()
    Shape.Paper -> opponent
    Shape.Scissors -> opponent.lose()
}

fun Shape.lose() = when(this) {
    Shape.Rock -> Shape.Paper
    Shape.Paper -> Shape.Scissors
    Shape.Scissors -> Shape.Rock
}

fun Shape.win() = when(this) {
    Shape.Rock -> Shape.Scissors
    Shape.Paper -> Shape.Rock
    Shape.Scissors -> Shape.Paper
}

// A fun alternative to ``invoke()``
fun Char.toShape() = when(this) {
    'A','X' -> Shape.Rock
    'B','Y' -> Shape.Paper
    'C','Z' -> Shape.Scissors
    else -> null
}

fun main() {
    val puzzleLines = File("puzzleinput.txt").absoluteFile.readLines()

    val pt1 = puzzleLines.sumOf { Shape(it[0])?.let { elf -> Shape(it[2])?.score(elf) } ?: 0 }
    val pt2 = puzzleLines.sumOf { Shape(it[0])?.let { elf -> Shape(it[2])?.decision(elf)?.score(elf) } ?: 0 }
    println("Score for part 1: $pt1")
    println("Score for part 2: $pt2")
}

main()