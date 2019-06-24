package deepfinity.android.di.scope

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(value = AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
annotation class ServiceScope