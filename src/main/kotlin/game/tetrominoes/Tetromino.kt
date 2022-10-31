package game.tetrominoes

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.config.GameMatrix
import utils.random.randomColor
import java.awt.Point

abstract class Tetromino : KoinComponent {
    private val gameMatrix: GameMatrix by inject()

    abstract val rotations: List<Array<IntArray>>
    private var currentRotationNum = 0

    fun currentRotation(): Array<IntArray> = rotations[currentRotationNum]
    fun possibleNextRotation(): Array<IntArray> = if (currentRotationNum + 1 >= rotations.size) rotations[0] else rotations[currentRotationNum + 1]
    fun rotate() { if (++currentRotationNum >= rotations.size) currentRotationNum = 0 }

    val color = randomColor()
    val point: Point = Point((gameMatrix.width / 2), 0)
}