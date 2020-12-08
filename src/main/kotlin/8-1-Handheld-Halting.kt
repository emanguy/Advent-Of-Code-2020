import lib.ConsoleState
import lib.consoleCompile
import lib.consoleExecStep
import java.io.File

fun main() {
    val instructions = consoleCompile(File("inputs/official/8-1-handheld-halting.txt").readLines())
    val previouslyExecutedInstructions = mutableSetOf<Int>()

    var currentState = ConsoleState()
    while (true) {
        previouslyExecutedInstructions += currentState.instructionIdx
        val nextState = consoleExecStep(instructions, currentState)
        if (nextState.instructionIdx in previouslyExecutedInstructions) break
        currentState = nextState
    }

    println("Acc register value: ${currentState.accRegister}")
}