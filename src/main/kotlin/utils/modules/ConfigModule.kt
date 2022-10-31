package utils.modules

import com.sksamuel.hoplite.ConfigLoader
import org.koin.dsl.module
import utils.config.Config

val configModule = module {
    single<Config> { ConfigLoader().loadConfigOrThrow("/config.conf") }
    single { get<Config>().tetrominoSpeed }
    single { get<Config>().gameMatrix }
    single { get<Config>().numberOfPossibleRotations }
    single { get<Config>().numberOfTicksBeforeNewTetromino }
}