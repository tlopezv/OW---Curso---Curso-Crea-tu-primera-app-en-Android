package com.udemy.cursokotlin.miprimeraapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// La Clase "ViewHolder" va a recibir una "View" que será la celda que va a pintar
// Y debe extender de la clase ViewHolder
class TaskViewHolder(view: View):RecyclerView.ViewHolder(view) {

    // Accediendo a la view que nos están dando para este "ViewHolder", que sería el
    // item que se está pintando, buscamos uno de los componentes de dicha "View"
    private val tvTask:TextView = view.findViewById(R.id.tvTask)
    private val ivTaskDone:ImageView = view.findViewById(R.id.ivTaskDone)

    // La siguiente función la útilizará el "Adapter" que pasará al texto que tendrá el "Adapter"
    // Se conectará el "ViewHolder" al "Activity" a través de un "Adapter"
    fun render(task:String, onItemDone:(Int)->Unit){
        tvTask.text = task
        // Cuándo pulsemos la imagen, llamamos a la función a ejecutar para borrar dicha tarea
        ivTaskDone.setOnClickListener { onItemDone(adapterPosition) }
    }
}