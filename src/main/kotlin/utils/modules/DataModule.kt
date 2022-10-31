package utils.modules

import org.koin.dsl.module
import utils.data.IsHelpVisible

val dataModule = module {
    single { IsHelpVisible(false) }
}