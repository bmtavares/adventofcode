import java.io.File

var sum = 0

File("puzzleinput.txt").bufferedReader().forEachLine {
    sum += Regex("mul\\(\\d{1,3},\\d{1,3}\\)").findAll(it).map {
        Regex("(\\d{1,3})").findAll(it.value).let {
            it.first().value.toInt() * it.last().value.toInt()
        }
    }.sum()
}

println(sum)
