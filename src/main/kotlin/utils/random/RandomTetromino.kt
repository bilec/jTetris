package utils.random

import game.tetrominoes.*
import kotlin.reflect.full.createInstance

fun randomTetromino(): Tetromino = listOf(
    BlueRicky::class,
    ClevelandZ::class,
    Hero::class,
    OrangeRicky::class,
    RhodeIslandZ::class,
    SmashBoy::class,
    TeeWee::class
).random().createInstance()