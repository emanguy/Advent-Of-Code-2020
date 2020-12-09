import java.io.File
import java.util.*

fun main() {
    val inputNumbers = File("inputs/official/9-1-encoding-error.txt").readLines().map { it.toLong() }
    val preambleLength = 25

    val sumWindow = LinkedList<Long>()
    val windowRandomAccess = mutableSetOf<Long>()

    // Ingest preamble
    for (idx in 0 until preambleLength) {
        sumWindow.addLast(inputNumbers[idx])
        windowRandomAccess.add(inputNumbers[idx])
    }

    // Iterate over remaining numbers until one doesn't satisfy the summation condition
    for (number in inputNumbers.subList(preambleLength, inputNumbers.size)) {
        // Find a number in the window that satisfies the sum condition
        var numbersInWindowAreSum = false
        for (windowNum in sumWindow) {
            if ((number - windowNum) in windowRandomAccess) {
                numbersInWindowAreSum = true
                break
            }
        }

        // If no numbers in the window sum to the target, print it
        if (!numbersInWindowAreSum) {
            println("Found a number that does not have a sum in the window: $number")
            return
        }

        // Shift the window
        val droppedNumber = sumWindow.removeFirst()
        windowRandomAccess.remove(droppedNumber)
        sumWindow.addLast(number)
        windowRandomAccess.add(number)
    }

    println("All numbers satisfy the condition.")
}