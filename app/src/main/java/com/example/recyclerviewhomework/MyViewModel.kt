package com.example.recyclerviewhomework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ItemEvent {ADD, UPDATE, DELETE}
data class Item(val firstname: String, val lastname: String)

class MyViewModel : ViewModel(){
    val clickItem = MutableLiveData<Int>()
    val itemsListData = MutableLiveData<ArrayList<Item>>()
    var itemsEvent = ItemEvent.ADD
    var itemsEventPos = -1

    val items = ArrayList<Item>()
    var itemLongClick =-1

    fun addItem(item : Item) {
        itemsEvent = ItemEvent.ADD
        itemsEventPos = items.size
        items.add(item)
        itemsListData.value = items
    }

    fun updateItem(pos: Int, item: Item) {
        itemsEvent = ItemEvent.UPDATE
        itemsEventPos = pos
        items[pos] = item
        itemsListData.value = items
    }

    fun deleteItem(pos: Int) {
        itemsEvent = ItemEvent.DELETE
        itemsEventPos = pos
        items.removeAt(pos)
        itemsListData.value = items
    }
}