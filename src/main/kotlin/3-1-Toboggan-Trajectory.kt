import lib.Slope
import lib.getTreesAlongSlope
import java.io.File

fun main() {
    val inputLines = File("inputs/official/3-1-toboggan-trajectory.txt").readLines()
    val slope = Slope(1, 3)

    val treesEncountered = getTreesAlongSlope(inputLines, slope)

    println("Encountered $treesEncountered trees.")
}

