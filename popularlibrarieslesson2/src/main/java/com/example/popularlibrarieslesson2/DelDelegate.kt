package com.example.popularlibrarieslesson2

import kotlin.reflect.KProperty

class DelDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, спасибо за делегирование мне ${property.name} !"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>,value: String){
    }
}