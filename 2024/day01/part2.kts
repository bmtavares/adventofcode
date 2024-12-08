import java.io.File

val links = mutableListOf<Int>()
val rechts = mutableMapOf<Int, Int>()

File("puzzleinput.txt").bufferedReader().forEachLine {
    val coords = it.split("   ")
    links.add(coords.first().toInt())
    val right = coords.last().toInt()
    rechts[right] = rechts.getOrDefault(right, 0).inc()
}

val sum = links.fold(0) { acc, it ->
    acc + it * rechts.getOrDefault(it, 0)
}

println(sum)