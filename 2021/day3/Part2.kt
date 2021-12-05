import java.io.File

fun main() {
    val puzzleLines = File("puzzleinput.txt").readLines()
    val (oneCommonList, zeroCommonList) = puzzleLines.partition {it[0] == '1'}

    val oxygenBits : String
    val co2Bits : String

    if (zeroCommonList.size > oneCommonList.size) {
        oxygenBits = findX(zeroCommonList, 1, true)
        co2Bits = findX(oneCommonList, 1, false)
    }
    else {
        oxygenBits = findX(oneCommonList, 1, true)
        co2Bits = findX(zeroCommonList, 1, false)
    }

    print(oxygenBits.toInt(2) * co2Bits.toInt(2))
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