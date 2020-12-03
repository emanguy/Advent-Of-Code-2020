import lib.Slope
import lib.getTreesAlongSlope
import java.io.File

fun main() {
    val inputLines = File("inputs/official/3-1-toboggan-trajectory.txt").readLines()
    val slopes = listOf(
        Slope(1, 1),
        Slope(1, 3),
        Slope(1, 5),
        Slope(1, 7),
        Slope(2, 1)
    )
    val encounteredTrees = mutableListOf<Int>()

    for (slope in slopes) {
        encounteredTrees += getTreesAlongSlope(inputLines, slope)
    }

    val multipliedTotal = encounteredTrees.map { it.toLong() }.reduce { total, next -> total * next }
    println("Encountered these numbers of trees along the slopes: $encounteredTrees. Multiplied total is $multipliedTotal.")
}

