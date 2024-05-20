package com.android.esercitazione24_05_06

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HotelListViewModel(application: Application) : AndroidViewModel(application) {

    private val itemList = MutableLiveData<ArrayList<Hotel>>()

    private val itemListPreferiti = MutableLiveData<ArrayList<Hotel>>()
    
    val preferiti = MutableLiveData<Boolean>()

    init {
        this.preferiti.value = false
        this.itemListPreferiti.value = ArrayList()
        val string = readJsonFromAssets(application.applicationContext, "hotels.json")
        this.itemList.value = parseJsonToModel(string)
    }

    fun getItemList(): LiveData<ArrayList<Hotel>> {
        return itemList
    }

    fun getItemListPreferiti(): LiveData<ArrayList<Hotel>> {
        return itemListPreferiti
    }

    fun addItem(item: Hotel) {
        itemList.value?.add(item)
    }

    fun removeItem(item: Hotel) {
        itemList.value?.remove(item)
    }

    fun addItemPreferiti(item: Hotel) {
        itemListPreferiti.value?.add(item)
    }

    fun removeItemPreferiti(item: Hotel) {
        itemListPreferiti.value?.remove(item)
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun parseJsonToModel(jsonString: String): ArrayList<Hotel> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<ArrayList<Hotel>>() {}.type)
    }
}