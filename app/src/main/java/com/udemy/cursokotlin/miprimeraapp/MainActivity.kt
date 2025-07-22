package com.udemy.cursokotlin.miprimeraapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udemy.cursokotlin.miprimeraapp.TaskApplication.Companion.prefs

class MainActivity : AppCompatActivity() {

    // Vamos a utilizar variables que inicializaremos después (lateinit) para recuperar los componentes
    // de nuestra ventana o activity asociada
    lateinit var btnAddTask:Button
    lateinit var etTask: EditText
    lateinit var rvTasks: RecyclerView

    lateinit var adapter:TaskAdapter

    var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // La siguiente instrucción une el Layout a esta clase
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
    }

    private fun initUI() {
        initView()
        initListeners()
        initRecyclerView()
    }

    private fun initView(){
        // Instanciamos los componentes del Layout
        // NOTA: El fichero R es dónde se guardan todas las referencias
        btnAddTask = findViewById(R.id.btnAddTask)
        etTask = findViewById(R.id.etTask)
        rvTasks = findViewById(R.id.rvTasks)
    }

    private fun initListeners(){
        btnAddTask.setOnClickListener {
            addTask()
        }
    }

    private fun addTask(){
        val taskToAdd:String = etTask.text.toString()
        // Añadimos la tarea a la lista de Tareas
        tasks.add(taskToAdd)
        // Avisamos al "Adapter" que se han añadido más tareas al listado de tareas que está
        // utilizando, para que pinte de nuevo la lista
        adapter.notifyDataSetChanged()
        // Limpiamos el campo de edición
        etTask.setText("")
        // Guardamos el nuevo listado a las preferencias
        prefs.saveTasks(tasks)
    }

    // Configura el "RecyclerView" para añadirle el "Adapter"
    private fun initRecyclerView(){
        // Lo primero vamos a ir a las prferencias para ver si tenía tareas ya guardadas
        tasks = prefs.getTasks()
        // añadimos un "LayoutManager" al "RecyclerView"
        // Un "LayoutManager" es el encargado de mostrar como se van a ver las vistas
        // Utilizamos un "Layout" que muestre una lista de componentes vertical
        rvTasks.layoutManager = LinearLayoutManager(this)
        // Instancia de nuestro adapter que necesita nuestra lista de tareas
        // Paso 2, el segundo parámetro es la función a ejecutar para borrar la tarea que hemos pulsado
        adapter = TaskAdapter(tasks) { deleteTask(it) }
        // Y le decimos al "RecyclerView" que utilice dicho "Adapter"
        rvTasks.adapter = adapter

    }

    private fun deleteTask(position:Int){
        tasks.removeAt(position)
        adapter.notifyDataSetChanged()
        // Guardamos el nuevo listado a las preferencias
        prefs.saveTasks(tasks)
    }

    var name:String? = null

    fun nulabilidad(){
        name = "Aris"
    }

    fun test(){
        // Si "name" no es nulo dame la longitud
        var longitud = name?.length
        // También podemos utilizar el operador Elvis ?: permite que si la parte de su izquierda
        // es nula devuelva la expresión de su derecha
        longitud = name?.length ?: 4
    }
}