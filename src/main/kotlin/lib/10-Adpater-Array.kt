package lib

data class LargestAdapterResult(val largestAdapterSize: Int, val differenceCounts: Map<Int, Int> = emptyMap())

fun getLargestAdapter(sortedAdapters: List<Int>, currentAdapterIdx: Int = 0, memoizationMap: MutableMap<Int, LargestAdapterResult> = mutableMapOf()): LargestAdapterResult {
    // If we've already solved for the largest adapter from this point, just return the result we got last time
    val memoizedAnswer = memoizationMap[currentAdapterIdx]
    if (memoizedAnswer != null) return memoizedAnswer

    // Otherwise, pick the next adapter that will maximize final adapter size

    // The offset from the current index to attempt
    var pickOffset = 1

    // Return immediately if the next pick would be the end of the list
    if (currentAdapterIdx + pickOffset >= sortedAdapters.size) {
        val result = LargestAdapterResult(sortedAdapters[currentAdapterIdx])
        memoizationMap[currentAdapterIdx] = result
        return result
    }

    // The difference in adapter power between this adapter and the next
    var nextAdapterDiff = sortedAdapters[currentAdapterIdx + pickOffset] - sortedAdapters[currentAdapterIdx]
    // Current pick for largest adapter size (defaults to the current adapter)
    var maxPossibleAdapter = LargestAdapterResult(sortedAdapters[currentAdapterIdx])
    // The difference in index between the current index and the max pick index
    var maxPossibleAdapterPickOffset = 0

    // We need the difference to stay within 3, otherwise the adapters are incompatible
    while (nextAdapterDiff <= 3) {
        val largestAdapterForPick = getLargestAdapter(sortedAdapters, currentAdapterIdx + pickOffset, memoizationMap)

        if (largestAdapterForPick.largestAdapterSize > maxPossibleAdapter.largestAdapterSize) {
            maxPossibleAdapter = largestAdapterForPick
            maxPossibleAdapterPickOffset = pickOffset
        }
        pickOffset++
        if (currentAdapterIdx + pickOffset >= sortedAdapters.size) break
        nextAdapterDiff = sortedAdapters[currentAdapterIdx + pickOffset] - sortedAdapters[currentAdapterIdx]
    }

    // Determine the difference between the current adapter and the max choice. A difference of 0 means we can't
    // get a larger adapter than this one.
    val maxPossibleAdapterDiff = sortedAdapters[currentAdapterIdx + maxPossibleAdapterPickOffset] - sortedAdapters[currentAdapterIdx]
    val diffMapUpdate = if (maxPossibleAdapterDiff == 0) {
        maxPossibleAdapter.differenceCounts
    } else {
        val newMap = maxPossibleAdapter.differenceCounts.toMutableMap()
        val oldDiffValue = newMap.getOrDefault(maxPossibleAdapterDiff, 0)
        newMap[maxPossibleAdapterDiff] = oldDiffValue + 1

        newMap
    }

    println("FROM ${sortedAdapters[currentAdapterIdx]} PICK ${sortedAdapters[currentAdapterIdx + maxPossibleAdapterPickOffset]}")
    // Memoize and return the largest adapter result
    val largestAdapterResult = maxPossibleAdapter.copy(differenceCounts = diffMapUpdate)
    memoizationMap[currentAdapterIdx] = largestAdapterResult
    return largestAdapterResult
}