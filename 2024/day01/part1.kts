import java.io.File
import kotlin.math.absoluteValue

val links = mutableListOf<Int>()
val rechts = mutableListOf<Int>()

File("puzzleinput.txt").bufferedReader().forEachLine {
    val coords = it.split("   ")
    links.add(coords.first().toInt())
    rechts.add(coords.last().toInt())
}

links.sort()
rechts.sort()

val sum = links.foldIndexed(0) { idx, acc, it ->
    acc + (it - rechts[idx]).absoluteValue
}

println(sum)