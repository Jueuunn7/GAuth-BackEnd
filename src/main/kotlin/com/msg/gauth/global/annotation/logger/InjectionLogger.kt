package com.msg.gauth.global.annotation.logger

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class InjectionLogger: ApplicationListener<ApplicationStartedEvent> {
    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        val ac = event.applicationContext
        val beanDefinitionNames = ac.beanDefinitionNames
        for (name in beanDefinitionNames) {
            val bean = ac.getBean(name!!)
            val classPath = bean.javaClass.name
            val log = LoggerFactory.getLogger(classPath)
            try {
                if (!classPath.contains("gauth")) {
                    continue
                }
                val beanClass = Class.forName(classPath)
                val fields = beanClass.declaredFields
                for (field in fields) {
                    val log4k = field.getDeclaredAnnotation(log4k::class.java)
                    if (log4k != null) {
                        field.isAccessible = true
                        field[bean] = log
                    }
                }
            } catch (e: ClassNotFoundException) {
                throw RuntimeException(e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException(e)
            }
        }
    }
}