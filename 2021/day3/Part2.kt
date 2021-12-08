import java.io.File

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()

    val oxygenBits = findX(puzzleLines, 0, true).toInt(2)
    val co2Bits = findX(puzzleLines, 0, false).toInt(2)

    print(oxygenBits * co2Bits)
}

fun findX (listToSearch: List<String>, idx : Int, isOxygen: Boolean) : String {
    val (oneCommonList, zeroCommonList) = listToSearch.partition { it[idx] == '1' }

    val foundList = if (isOxygen) {
        if (zeroCommonList.size > oneCommonList.size)
            zeroCommonList
        else
            oneCommonList
    }
    else {
        if (zeroCommonList.size > oneCommonList.size)
            oneCommonList
        else
            zeroCommonList
    }

    if (foundList.size > 1) {
        return findX(foundList, idx + 1, isOxygen)
    }

    return foundList.first()
}
