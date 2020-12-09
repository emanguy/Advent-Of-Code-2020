import java.io.File
import java.util.*

fun main() {
    val inputNumbers = File("inputs/official/9-1-encoding-error.txt").readLines().map { it.toLong() }
    val targetSum: Long = 530627549

    // Assemble initial sum window
    val sumWindow = LinkedList<Long>().apply {
        addLast(inputNumbers[0])
        addLast(inputNumbers[1])
    }
    var currentSum: Long = sumWindow.sum()
    var listHeadIdx = 2

    /*
    Follow these rules to expand and contract the window:

    If the current sum of the window is too low or the length is 2, expand the window.
    If the current sum of the window is too high, contract the window.

    Contracting always moves the tail of the sum window forward, while expanding moves the head
    of the sum window forward.
     */
    while (listHeadIdx < inputNumbers.size) {
        if (currentSum == targetSum) {
            val largest = sumWindow.maxOrNull() ?: error("No entries in list to find max!")
            val smallest = sumWindow.minOrNull() ?: error("No entries in list to find min!")
            println("Got largest and smallest sum: ${largest + smallest} with window: $sumWindow")
            return
        }

        // Expand rule
        if (sumWindow.size == 2 || currentSum < targetSum) {
            sumWindow.addLast(inputNumbers[listHeadIdx])
            currentSum += inputNumbers[listHeadIdx]
            listHeadIdx++
        } else { // Contract rule
            val droppedNumber = sumWindow.removeFirst()
            currentSum -= droppedNumber
        }
    }

    println("Failed to find a contiguous sum that hits the target number.")
}