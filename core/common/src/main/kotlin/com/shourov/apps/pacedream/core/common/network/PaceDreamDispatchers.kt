package com.shourov.apps.pacedream.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val fauDispatcher: PaceDreamDispatchers)

enum class PaceDreamDispatchers {
    Default,
    IO,
}
