package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.ToDosAdapter
import com.example.myapplication.data.ToDoRepository
import com.example.myapplication.helpers.errorLog
import com.example.myapplication.models.dbEntities.ToDoItem
import com.example.myapplication.models.ToDoItemElemnt

class LocalDataBaseActivity : AppCompatActivity() {

    private var button: Button? = null
    private var editTextTitle: EditText? = null
    private var editTextAuthor:EditText? =null
    private var editTextDescription: EditText? =null
    private val toDoRepository = ToDoRepository()
    private var progressBar: ProgressBar? = null

    private val todoList by lazy {
        ArrayList<ToDoItemElemnt>()
    }

    private var todoAdapter: ToDosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_data_base)
        setupViews()
    }
    override fun onStart(){
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
    fun setupViews() {
        setupRecyclerView()

        button = findViewById(R.id.button_data)
        editTextTitle = findViewById(R.id.name)
        editTextAuthor=findViewById(R.id.author)
        editTextDescription=findViewById((R.id.description))
        button?.setOnClickListener {
            insertToDo()
        }

        findViewById<Button>(R.id.button_data).setOnClickListener {
            deleteByTitleItem()
        }
        progressBar = findViewById(R.id.pb_loading)
    }
    private fun setupRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.todos)
        val layoutManager: LinearLayoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        //val layoutManager: GridLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, true)
        recyclerView.layoutManager = layoutManager

        todoAdapter = ToDosAdapter(todoList)
        recyclerView.adapter = todoAdapter
}

    fun getToDos() {
        progressBar?.visibility = View.VISIBLE
        toDoRepository.getAllToDo { toDoList ->
            Log.e("Eroare", "Aceasta e o eroare!")
            "Aceasta e o eroare!".errorLog()
            if (toDoList.size > 0) {
                val toDoItem = toDoList.get(0)
                toDoItem.title = "to do done"
                toDoItem.description = "to do done"
                updateItem(toDoItem)
            }
            if (toDoList.size > 1) {
                val toDoItem = toDoList.get(1)
                deleteItem(toDoItem)
            }
        }
    }

    fun updateItem(toDoItem: ToDoItem) {
        if (toDoItem.title != null && toDoItem.author != null) {
            toDoRepository.updateToDo(toDoItem, object : ToDoRepository.OnSuccesListener {
                override fun onSuccess() {
                    "Succes".errorLog()
                    progressBar?.visibility = View.GONE
                }
            })
        }
    }

    fun deleteItem(toDoItem: ToDoItem) {
        toDoRepository.deleteToDo(toDoItem) {
            "Delete".errorLog()
        }
    }

    fun deleteByTitleItem() {
        val title = editTextTitle?.text?.toString() ?: return
        when (title.isEmpty()) {
            true -> return
        }

        toDoRepository.deleteByTitleToDo(title) {
            Toast.makeText(
                this, "Deleted.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun insertToDo() {
        val title = editTextTitle?.text?.toString() ?: return
        when (title.isEmpty()) {
            true -> {
                editTextTitle?.error = getString(R.string.error_required)
                return
            }
            false -> editTextTitle?.error = null
        }
        val author=editTextAuthor?.text?.toString() ?:return
        when(author.isEmpty()){
            true->{
                editTextAuthor?.error=getString(R.string.error_required)
                return
            }
            false->editTextAuthor?.error=null
        }

        val description = editTextDescription?.text?.toString() ?: return
        when (description.isEmpty()) {
            true -> {
                editTextDescription?.error = getString(R.string.error_required)
                return
            }

            false -> editTextDescription?.error = null
        }

       val toDoItem= ToDoItem(title,author, description)

        toDoRepository.insertToDo(toDoItem) {
            Toast.makeText(
                this, "Success.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}
