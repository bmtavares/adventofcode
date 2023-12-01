import java.io.File

fun main() {
    val days = 256
    val parentAge = 0
    val recoveryAge = 6
    val babyAge = 8
    var puzzleFishies =
            File("puzzleinput.txt").readLines()[0].split(',')
                    .map { it.toInt() }
                    .groupBy { it }
                    .mapValues { it.value.size.toLong() }
                    .toMutableMap()
    (parentAge..babyAge).forEach { if (!puzzleFishies.containsKey(it)) puzzleFishies.put(it, 0) }

    (1..days).forEach {
        val parents = puzzleFishies.get(parentAge)!!
        (parentAge until babyAge).forEach { puzzleFishies.stepDown(it) }
        puzzleFishies.put(recoveryAge, puzzleFishies.get(recoveryAge)!! + parents)
        puzzleFishies.put(babyAge, parents)
    }

    println(puzzleFishies.values.sum())
}

fun MutableMap<Int, Long>.stepDown(i: Int) {
    this.put(i, this.get(i + 1)!!)
}
