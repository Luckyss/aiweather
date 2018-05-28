package ru.androidpirates.aiweather.presentation.mvp.viewstate

import ru.androidpirates.aiweather.common.extensions.runOnMain
import ru.androidpirates.aiweather.presentation.mvp.BaseContract
import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ActionConfig
import ru.androidpirates.aiweather.presentation.mvp.viewstate.action.ViewAction
import ru.androidpirates.aiweather.presentation.mvp.viewstate.scenario.AddToEndSingleScenario
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.reflect.full.createInstance

@Suppress("UNCHECKED_CAST")
class ViewState<T : BaseContract.View>(private val cls: Class<T>) {
    private val actionsQueue: MutableCollection<Pair<String, ViewAction>> = ConcurrentLinkedQueue()
    private val viewProxy = Proxy.newProxyInstance(cls.classLoader, arrayOf(cls),
            RegistryInQueueHandler()) as T

    private var viewReference: WeakReference<T>? = null

    fun attachView(view: T) {
        viewReference = WeakReference(view)
        runOnMain { actionsQueue.forEach { it.second.call() } } // always call on main thread
    }

    fun detachView() {
        viewReference?.clear()
        viewReference = null
    }

    fun getView(): T {
        return viewProxy
    }

    private fun getRealView(): T? {
        return viewReference?.get()
    }

    inner class RegistryInQueueHandler : InvocationHandler {

        override fun invoke(proxy: Any, method: Method?, args: Array<Any>?): Any? {
            val srcClass = cls
            val srcClassAnnotation = srcClass.getAnnotation(ActionConfig::class.java) ?: null
            val srcClassScenario = srcClassAnnotation?.scenario?.createInstance()

            val srcMethod = srcClass.getMethod(method?.name, *method?.parameterTypes.orEmpty())
            val srcMethodAnnotation = srcMethod?.getAnnotation(ActionConfig::class.java)
            val srcMethodScenario = srcMethodAnnotation?.scenario?.createInstance()

            val scenario = srcMethodScenario ?: srcClassScenario ?: AddToEndSingleScenario()
            val tag = if (srcMethodAnnotation?.tag.isNullOrBlank()) {
                srcClassAnnotation?.tag ?: srcMethod?.name ?: "default_method_tag"
            } else {
                srcMethodAnnotation?.tag ?: "default_method_tag"
            }

            val action = object : ViewAction {
                override val tag: String = tag
                override fun call() {
                    getRealView()?.run { method?.invoke(this, *args.orEmpty()) }
                }
            }

            scenario.beforeCall(actionsQueue, action)
            var result: Any? = null
            runOnMain { result = action.call() } // always call on main thread
            scenario.afterCall(actionsQueue, action)

            return result
        }
    }

    companion object {
        @JvmStatic fun <V : BaseContract.View> create(presenterClass: Class<*>): ViewState<V> {
            try {

                // Scan the inheritance hierarchy until we reached BaseContract.Presenter
                var viewClass: Class<V>? = null
                var currentClass: Class<*> = presenterClass

                while (viewClass == null) {

                    var genericSuperType = currentClass.genericSuperclass

                    while (genericSuperType !is ParameterizedType) {
                        // Scan inheritance tree until we find ParameterizedType which is probably a
                        // BaseContract.View
                        currentClass = currentClass.superclass
                        genericSuperType = currentClass.genericSuperclass
                    }

                    val types = genericSuperType.actualTypeArguments

                    for (type in types) {
                        val genericType = type as Class<*>
                        if (genericType.isInterface && isSubTypeOfBaseContractView(genericType)) {
                            viewClass = genericType as Class<V>
                            break
                        }
                    }

                    // Continue with next class in inheritance hierarchy (see genericSuperType
                    // assignment at start of while loop)
                    currentClass = currentClass.superclass
                }
                return ViewState(viewClass)
            } catch (t: Throwable) {
                throw IllegalArgumentException(
                        "The generic type <V : BaseContract.View> must be the first generic type " +
                                "argument of class ${presenterClass.simpleName} (per convention)." +
                                " Otherwise we can't determine which type of View this Presenter " +
                                "coordinates.", t)
            }
        }

        /**
         * Scans the interface inheritance hierarchy and checks if on the root is BaseContract.View
         *
         * @param cls The leaf interface where to begin to scan
         * @return true if subtype of BaseContract.View, otherwise false
         */
        @JvmStatic private fun isSubTypeOfBaseContractView(cls: Class<*>): Boolean {
            if (cls == BaseContract.View::class.java) {
                return true
            }
            cls.interfaces.forEach {
                if (isSubTypeOfBaseContractView(it)) {
                    return true
                }
            }
            return false
        }
    }
}