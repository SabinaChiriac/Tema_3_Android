package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.ToDoRepository
import com.example.myapplication.helpers.errorLog
import com.example.myapplication.models.dbEntities.ToDoItem

class LocalDataBaseActivity : AppCompatActivity() {

    private var button: Button? = null
    private var editText: EditText? = null
    private val toDoRepository = ToDoRepository()
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_data_base2)
        setupViews()
    }
    fun setupViews() {
        button = findViewById(R.id.button_data)
        editText = findViewById(R.id.editable_text)
        button?.setOnClickListener {
            insertToDo()
        }
        findViewById<Button>(R.id.button_get).setOnClickListener {
            getToDos()
        }
        findViewById<Button>(R.id.button_delete).setOnClickListener {
            deleteByTitleItem()
        }
        progressBar = findViewById(R.id.pb_loading)
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
        toDoRepository.updateToDo(toDoItem, object : ToDoRepository.OnSuccesListener {
            override fun onSuccess() {
                "Succes".errorLog()
                progressBar?.visibility = View.GONE
            }
        })
    }

    fun deleteItem(toDoItem: ToDoItem) {
        toDoRepository.deleteToDo(toDoItem) {
            "Delete".errorLog()
        }
    }

    fun deleteByTitleItem() {
        val title = editText?.text?.toString() ?: return
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
        val title = editText?.text?.toString() ?: return
        when (title.isEmpty()) {
            true -> return
        }

        val toDoItem = ToDoItem(
            title,
            "descriere"
        )

        toDoRepository.insertToDo(toDoItem) {
            Toast.makeText(
                this, "Success.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}
