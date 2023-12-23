package org.isk

data class Matrix(
    val elements: Array<DoubleArray>
) {
    private val dimension: Pair<Int, Int> = Pair(elements.size, elements.first().size)
    init {
        elements.forEach {
            assert(it.size == elements.first().size){
                Throwable("The matrix rows should be of equal size.")
            }
        }
    }

    operator fun plus(other: Matrix){
        assert(this isOfSize other.dimension ){
            Throwable("Two matrices of different dimension can't be summed.")
        }
        

    }

    infix fun isOfSize(other: Pair<Int, Int>): Boolean =
        if (elements.size == other.first)
            elements.first().size == other.second
        else
            false

}
