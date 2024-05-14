package com.example.recyclerviewhomework

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        viewModel.addItem(Item("Jhon", "Baker"))
        val adapter = CustomAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(this)

        recyclerView.setHasFixedSize(true)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            ItemDialog().show(supportFragmentManager, "ItemDialog")
        }

        viewModel.itemsListData.observe(this) {
            when(viewModel.itemsEvent) {
                ItemEvent.ADD -> adapter.notifyItemInserted(viewModel.itemsEventPos)
                ItemEvent.UPDATE -> adapter.notifyItemChanged(viewModel.itemsEventPos)
                ItemEvent.DELETE -> adapter.notifyItemRemoved(viewModel.itemsEventPos)
            }
        }

        viewModel.clickItem.observe(this){
             // val item = viewModel.items[it]
             ItemDialog(it).show(supportFragmentManager, "")
        }


        registerForContextMenu(recyclerView)


    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.ctx_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete -> viewModel.deleteItem(viewModel.itemLongClick)
            R.id.edit -> viewModel.clickItem.value = viewModel.itemLongClick
            else -> return false
        }
        return true
    }
}