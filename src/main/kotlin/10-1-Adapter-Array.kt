import lib.getLargestAdapter
import java.io.File

fun main() {
    val inputNumbers = File("inputs/official/10-1-adapter-array.txt")
        .readLines()
        .map { it.toInt() }

    // Add the socket which MUST be picked
    val inputNumbersWithSocket = inputNumbers + 0
    val largestAdapter = getLargestAdapter(inputNumbersWithSocket.sorted())

    // Update the largest found adapter for the output joltage
    val outputJoltage = largestAdapter.largestAdapterSize + 3
    val updatedDiffMap = largestAdapter.differenceCounts.toMutableMap()
    val threesDiff = updatedDiffMap.getOrDefault(3, 0)
    updatedDiffMap[3] = threesDiff + 1

    println("Joltage output: $outputJoltage diffs: $updatedDiffMap")
}