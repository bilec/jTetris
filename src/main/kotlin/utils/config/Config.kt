package utils.config

data class Config (
    val appName: String,
    val gameWindowSize: GameWindowSize,
    val gameMatrix: GameMatrix,
    val tetrominoSpeed: TetrominoSpeed,
    val numberOfPossibleRotations: NumberOfPossibleRotations,
    val numberOfTicksBeforeNewTetromino: NumberOfTicksBeforeNewTetromino
)

data class GameWindowSize (val width: Int, val height: Int)
data class GameMatrix (val width: Int, val height: Int, val padding: Int, val cellSize: Int)
data class TetrominoSpeed (val normal: Int, val fast: Int)
data class NumberOfPossibleRotations(val value: Int)
data class NumberOfTicksBeforeNewTetromino(val value: Int)