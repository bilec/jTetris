package game.tetrominoes

class SmashBoy(
    override val rotations: List<Array<IntArray>> = listOf(
        arrayOf(
            intArrayOf(1,1),
            intArrayOf(1,1)
        )
    )
) : Tetromino()