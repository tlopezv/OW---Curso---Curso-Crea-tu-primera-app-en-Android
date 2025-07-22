package com.udemy.cursokotlin.miprimeraapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// El "Adapter" conectará el "ViewHolder" con toda la información del "Activity"
// Este recibirá la lista entera de Tareas, heredando de "RecyclerView.Adapter"
// Recibiendo esta última clase, el tipo "ViewHolder" con el que va a trabajar

// Evolución: Debemos pasarle también la función que se ejecutará para poder borrar una tarea
// la cuál me devolverá la posición del elemento a borrar de la lista que manejará como primer
// parámetro pasado
class TaskAdapter(private val tasks:List<String>, private val onItemDone: (Int)->Unit):RecyclerView.Adapter<TaskViewHolder>() {

    // Crea el "ViewHolder" y pinta el "item"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    //
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position],onItemDone)
    }

    // Esta función le dice al "RecyclerView" cuántos items tiene que mostrar
    override fun getItemCount() = tasks.size

}