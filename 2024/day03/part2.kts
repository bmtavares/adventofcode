import java.io.File

var sum = 0

var mulEnabled = true
File("puzzleinput.txt").bufferedReader().forEachLine {
    Regex("mul\\(\\d{1,3},\\d{1,3}\\)|don't\\(\\)|do\\(\\)").findAll(it).forEach {
        if (it.value == "do()" || it.value == "don't()") {
            mulEnabled = it.value == "do()"; return@forEach; }
        if (mulEnabled)
            Regex("(\\d{1,3})").findAll(it.value).let {
                sum += it.first().value.toInt() * it.last().value.toInt()
            }
    }
}

println(sum)
