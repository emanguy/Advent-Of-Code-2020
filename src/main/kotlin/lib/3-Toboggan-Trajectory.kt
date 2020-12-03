package lib

data class Slope(val dRow: Int, val dCol: Int)

fun getTreesAlongSlope(inputLines: List<String>, slope: Slope): Int {
    var row = 0
    var col = 0
    var treesEncountered = 0

    while (true) {
        row += slope.dRow
        if (row >= inputLines.size) break
        col = (col + slope.dCol) % inputLines[row].length
        if (inputLines[row][col] == '#') treesEncountered++
    }

    return treesEncountered
}