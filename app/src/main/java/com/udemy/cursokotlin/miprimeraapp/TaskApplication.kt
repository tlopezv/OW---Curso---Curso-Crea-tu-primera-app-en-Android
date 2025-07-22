package com.udemy.cursokotlin.miprimeraapp

import android.app.Application

// Extiende de "Application" esta será la 1a clase por la que pase nuestra App cuándo se inicie
// Esto se utiliza para instanciar cosas que tengamos que usar en todo el proyecto.
class TaskApplication: Application() {

    /**
     * Los objetos complementarios permiten definir funciones y propiedades a nivel de clase.
     * Esto facilita la creación de métodos de fábrica, el almacenamiento de constantes y el
     * acceso a utilidades compartidas.
     * >> https://kotlinlang.org/docs/object-declarations.html#companion-objects <<
     */
    companion object {
        lateinit var prefs:Preferences
    }

    override fun onCreate() {
        super.onCreate()
        // "baseContext" es el contexto de toda la aplicación
        prefs = Preferences(baseContext)
    }
}