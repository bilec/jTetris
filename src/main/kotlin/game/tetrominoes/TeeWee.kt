package game.tetrominoes

class TeeWee(
    override val rotations: List<Array<IntArray>> = listOf(
        arrayOf(
            intArrayOf(1,1,1),
            intArrayOf(0,1)
        ),
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,1),
            intArrayOf(0,1)
        ),
        arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,1,1)
        ),
        arrayOf(
            intArrayOf(1),
            intArrayOf(1,1),
            intArrayOf(1)
        )
    )
) : Tetromino()