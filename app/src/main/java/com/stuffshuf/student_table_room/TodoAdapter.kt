package com.stuffshuf.student_table_room

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_items.view.*


class TodoAdapter(var context: Context,var todo:ArrayList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){


    fun updateTasks(newTasks: ArrayList<Todo>) {
        todo.clear()
        todo.addAll(newTasks)
        notifyDataSetChanged()

    }
    var listItemClickList:ListItemClickList?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemvView=li.inflate(R.layout.list_items, parent, false)
        return TodoViewHolder(itemvView)
    }

    override fun getItemCount(): Int {

        return todo.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var todod=todo[position]
       holder.bind(todod)

        holder.itemView.del.setOnClickListener {

            listItemClickList?.delButtonListener(todod, position)

        Log.d("delbutton", "del $position")
        }

    }


   inner class TodoViewHolder(itemvView: View): RecyclerView.ViewHolder(itemvView)

    {
        fun bind(todo: Todo)
        {
            with(itemView)
            {
                firstName.text=todo.fName
                lastName.text=todo.lName
                emailName.text=todo.email
                phoneName.text=todo.number


            }
        }
    }


}