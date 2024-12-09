import java.io.File

val arr = mutableListOf<Array<Char>>()
val xmasList = arrayOf('X', 'M', 'A', 'S')
val samxList = xmasList.reversedArray()

fun findXmas(posX: Int, posY: Int): Int {
    var sum = 0
    if (posX > 2) {
        val verticalUp = arrayOf(arr[posX][posY], arr[posX-1][posY], arr[posX-2][posY], arr[posX-3][posY])
        if (verticalUp.contentEquals(xmasList) || verticalUp.contentEquals(samxList)) sum += 1

        if (posY > 2) {
            val diagonalUpLeft = arrayOf(arr[posX][posY], arr[posX-1][posY-1], arr[posX-2][posY-2], arr[posX-3][posY-3])
            if (diagonalUpLeft.contentEquals(xmasList) || diagonalUpLeft.contentEquals(samxList)) sum += 1
        }
        if (posY < arr[posX].size - 3) {
            val diagonalUpRight = arrayOf(arr[posX][posY], arr[posX-1][posY+1], arr[posX-2][posY+2], arr[posX-3][posY+3])
            if (diagonalUpRight.contentEquals(xmasList) || diagonalUpRight.contentEquals(samxList)) sum += 1
        }
    }
    if (posX < arr.size - 3) {
        val verticalDown = arrayOf(arr[posX][posY], arr[posX+1][posY], arr[posX+2][posY], arr[posX+3][posY])
        if (verticalDown.contentEquals(xmasList) || verticalDown.contentEquals(samxList)) sum += 1
        if (posY > 2) {
            val diagonalDownLeft = arrayOf(arr[posX][posY], arr[posX+1][posY-1], arr[posX+2][posY-2], arr[posX+3][posY-3])
            if (diagonalDownLeft.contentEquals(xmasList) || diagonalDownLeft.contentEquals(samxList)) sum += 1
        }
        if (posY < arr[posX].size - 3) {
            val diagonalDownRight = arrayOf(arr[posX][posY], arr[posX+1][posY+1], arr[posX+2][posY+2], arr[posX+3][posY+3])
            if (diagonalDownRight.contentEquals(xmasList) || diagonalDownRight.contentEquals(samxList)) sum += 1
        }
    }
    if (posY > 2) {
        val horizontalLeft = arr[posX].sliceArray(posY-3..posY)
        if (horizontalLeft.contentEquals(xmasList) || horizontalLeft.contentEquals(samxList)) sum += 1
    }
    if (posY < arr[posX].size - 3) {
        val horizontalRight = arr[posX].sliceArray(posY..posY+3)
        if (horizontalRight.contentEquals(xmasList) || horizontalRight.contentEquals(samxList)) sum += 1
    }
    return sum
}

File("puzzleinput.txt").bufferedReader().forEachLine {
    arr.add(it.toCharArray().toTypedArray())
}

val xmasc = arr.toTypedArray().mapIndexed { x, line ->
    line.mapIndexed { y, ch ->
        if (ch == 'X') findXmas(x, y) else 0
    }.sum()
}.sum()

println(xmasc)