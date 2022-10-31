package utils.modules

import org.koin.dsl.module

val allModules = module {
    includes(
        configModule,
        dataModule,
        guiModule
    )
}