import java.io.File

val arr = mutableListOf<Array<Char>>()
val masList = arrayOf('M', 'A', 'S')
val samList = masList.reversedArray()

fun findXMas(posX: Int, posY: Int): Boolean {
    if (posX in 1..arr.size-2 && posY in 1..arr[posX].size-2) {
        val first = arrayOf(arr[posX-1][posY-1], arr[posX][posY], arr[posX+1][posY+1])
        val second = arrayOf(arr[posX+1][posY-1], arr[posX][posY], arr[posX-1][posY+1])
        return (first.contentEquals(masList) || first.contentEquals(samList)) && (second.contentEquals(masList) || second.contentEquals(samList))
    }
    return false
}

File("puzzleinput.txt").bufferedReader().forEachLine {
    arr.add(it.toCharArray().toTypedArray())
}

val masc = arr.mapIndexed { x, line ->
    line.mapIndexed { y, ch ->
        if (ch == 'A' && findXMas(x, y)) 1 else 0
    }.sum()
}.sum()

println(masc)