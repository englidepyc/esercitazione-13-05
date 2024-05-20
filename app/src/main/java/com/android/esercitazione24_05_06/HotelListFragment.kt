package com.android.esercitazione24_05_06

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelListFragment : Fragment() {

    companion object {
        fun newInstance() = HotelListFragment()
    }

    private val viewModel: HotelListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_hotel_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.preferiti.observe(viewLifecycleOwner) {it ->
            val customAdapter =
            if(it){
                viewModel.getItemListPreferiti().value?.let { it1 -> CustomAdapter(it1) }
            } else {
                viewModel.getItemList().value?.let { it1 -> CustomAdapter(it1) }
            }
            recyclerView.adapter = customAdapter
            customAdapter?.setOnClickListener(object :
                CustomAdapter.OnClickListener {
                override fun onClick(position: Int, hotel: Hotel) {
                    if(it) {
                        viewModel.removeItemPreferiti(hotel)
                        viewModel.addItem(hotel)
                    }else {
                        viewModel.addItemPreferiti(hotel)
                        viewModel.removeItem(hotel)
                    }
                }
            })
        }

        view.findViewById<Button>(R.id.ListaBtn).setOnClickListener {
            viewModel.preferiti.value = false
        }


        view.findViewById<Button>(R.id.Preferiti_Btn).setOnClickListener {
            viewModel.preferiti.value = true
        }
    }
}