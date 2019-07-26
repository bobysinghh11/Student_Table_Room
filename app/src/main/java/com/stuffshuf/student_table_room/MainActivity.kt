package com.stuffshuf.student_table_room

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    var list= arrayListOf<Todo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        supportActionBar?.title="Student Data"


        val db= Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "todo.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        list=db.todoDao().getAllTodo()  as ArrayList<Todo>

       val todoAdapter = TodoAdapter(this,list)

     lvTodolist.adapter = todoAdapter




        btnAdd.setOnClickListener {

            db.todoDao().insertRow(
                Todo(
                    fName = firstname.text.toString(),
                    lName = lastame.text.toString(),
                    email = email.text.toString(),
                    number = number.text.toString()
                )
            )
            list = db.todoDao().getAllTodo() as ArrayList<Todo>
            // Log.d("list", "l $list")
             todoAdapter.updateTasks(list)
            lvTodolist.layoutManager = GridLayoutManager(
                this,
                1,
                GridLayoutManager.HORIZONTAL,
                false
            )
//            todoAdapter.updateTasks(list)

            lvTodolist.adapter = todoAdapter
        }

btnAdd.setOnClickListener {
    val intent=Intent(this, Main2Activity::class.java)
    intent.putExtra("Fname", firstname.text.toString())
    intent.putExtra("Lname", lastame.text.toString())
    intent.putExtra("E-Mail", email.text.toString())
    intent.putExtra("Number", number.text.toString())

          startActivity(intent)
}


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

                   searchView.setQuery(" ", false)

                searchView.setIconifiedByDefault(false)
                searchView.queryHint="Here!"
                Log.d("ne", "new $query")
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val tasks=newText
                Log.d("newtasks", "new $newText")
                return true
            }

        })
        return true
    }

}
