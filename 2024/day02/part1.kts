import java.io.File

var safeReports = 0

File("puzzleinput.txt").bufferedReader().forEachLine {
    var range: IntRange? = null
    it.split(" ").map(String::toInt).zipWithNext { a, b ->
        (a - b).let { safety ->
            if (safety == 0) return@forEachLine
            if (range == null) range = if (safety < 0) (-3..-1) else (1..3)
            if (safety !in range!!) return@forEachLine
        }
    }
    safeReports += 1
}

println(safeReports)
