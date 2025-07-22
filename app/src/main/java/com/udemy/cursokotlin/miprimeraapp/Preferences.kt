package com.udemy.cursokotlin.miprimeraapp

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    /**
     * Los objetos complementarios permiten definir funciones y propiedades a nivel de clase.
     * Esto facilita la creación de métodos de fábrica, el almacenamiento de constantes y el
     * acceso a utilidades compartidas.
     * >> https://kotlinlang.org/docs/object-declarations.html#companion-objects <<
     */
    companion object {
        const val PREFS_NAME = "myDataBase"
        const val TASKS = "tasks_value"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    // Método para guardar información
    fun saveTasks(tasks:List<String>){
        prefs.edit().putStringSet(TASKS, tasks.toSet()).apply()
    }

    fun getTasks():MutableList<String> {
        return prefs.getStringSet(TASKS, emptySet<String>())?.toMutableList() ?: mutableListOf()
    }
}