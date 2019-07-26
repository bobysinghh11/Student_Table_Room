package com.stuffshuf.student_table_room

import android.app.SearchManager
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main2.*

@Suppress("UNREACHABLE_CODE")
class Main2Activity : AppCompatActivity() {

    var list = arrayListOf<Todo>()

    val todoAdapter = TodoAdapter(this, list)

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "todo.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        val fName = intent.getStringExtra("Fname")
        val lName = intent.getStringExtra("Lname")
        val email = intent.getStringExtra("E-Mail")
        val number = intent.getStringExtra("Number")

//
//        val db = Room.databaseBuilder(
//            this,
//            AppDatabase::class.java,
//            "todo.db"
//        ).allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()

        list = db.todoDao().getAllTodo() as ArrayList<Todo>

        val todoAdapter = TodoAdapter(this, list)

        show.adapter = todoAdapter



        db.todoDao().insertRow(
            Todo(
                fName = fName,
                lName = lName,
                email = email,
                number = number
            )
        )
        list = db.todoDao().getAllTodo() as ArrayList<Todo>
        // Log.d("list", "l $list")
        todoAdapter.updateTasks(list)
        show.layoutManager = GridLayoutManager(
            this,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        todoAdapter.updateTasks(list)

        show.adapter = todoAdapter

        Log.d("list2", "2 $list")


        todoAdapter.listItemClickList = object : ListItemClickList {
            override fun checkBoxListener(todo: Todo, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun delButtonListener(todo: Todo, position: Int) {

                db.todoDao().delete(todo)
                list = db.todoDao().getAllTodo() as ArrayList<Todo>
                todoAdapter.updateTasks(list)
                show.adapter = todoAdapter
                Log.d("todos", "td $todo")
            }

        }

//        val manager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchItem= menu.find

search2.setOnQueryTextListener(object :android.widget.SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {
       return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val task= ""+newText+"%"

        list = db.todoDao().SearchTodo(task) as ArrayList<Todo>
        todoAdapter.updateTasks(list)
        show.adapter=todoAdapter

        return true
    }

})



//    override fun onQueryTextChange(newText: String?): Boolean {
//
//        val task= ""+newText+"%"
//
//        Log.d("news", "$newText")
//        list = db.todoDao().SearchTodo(task) as ArrayList<Todo>
//        todoAdapter.updateTasks(list)
//        show.adapter=todoAdapter
//
//        return true
//
//    }
//
//})

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater= menuInflater
        inflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)

        var manager =getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchItem=menu?.findItem(R.id.searcher)
        var searchView=searchItem?.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        // searchView.queryHint="Here!"


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

//                searchView.setQuery(" ", false)
//
//                searchView.setIconifiedByDefault(false)
//                searchView.queryHint="Here!"
//                Log.d("ne", "new $query")
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val tasks=""+newText+"%"
               list =  db.todoDao().SearchTodo(tasks!!) as ArrayList<Todo>
                todoAdapter.updateTasks(list)
                show.adapter=todoAdapter
                Log.d("newtasks", "new $newText")
                Log.d("newlist", "new $list")
                return true
            }

        })
        return true
    }


    }

