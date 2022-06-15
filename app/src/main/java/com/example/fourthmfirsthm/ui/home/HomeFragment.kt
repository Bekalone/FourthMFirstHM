package com.example.fourthmfirsthm.ui.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fourthmfirsthm.App
import com.example.fourthmfirsthm.R
import com.example.fourthmfirsthm.databinding.FragmentHomeBinding
import com.example.fourthmfirsthm.model.News
import java.util.*

import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: NewsAdapter
    private lateinit var tempArrayList: ArrayList<News>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var list = App.database.newsDao().getAll()
        binding.recyclerview.adapter = adapter

/*
        tempArrayList = arrayListOf<News>()
        tempArrayList.addAll(list as ArrayList<News>)
        adapter.addItems(tempArrayList)*/

        adapter.addItems(list as ArrayList<News>)


        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }

        /*binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    adapter.getData().forEach {
                        if (it.title.lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                    adapter!!.notifyDataSetChanged()
                } else{
                    tempArrayList.clear()
                    tempArrayList.addAll(list)
                    adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })*/

        onClickListenerRewrite()
        onClickListenerAlert()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickListenerRewrite() {
        adapter.onClickListener = {
            val bundle = bundleOf("key" to it.title)
            findNavController().navigate(R.id.newsFragment, bundle)
        }
    }

    private fun onClickListenerAlert() {
        adapter.onLongClickListener = { position ->
            val builder = android.app.AlertDialog.Builder(requireContext())
            builder.setTitle("Delete item")
            builder.setIcon(R.drawable.ic_warning)
            builder.setMessage(R.string.You_really_want_to_remove_item)
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { _, _ ->
                        App.database.newsDao().delete(adapter.getItem(position))
                        adapter.deleteItem(position)
                        adapter.notifyItemChanged(position)
                    }).setNegativeButton(R.string.no, DialogInterface.OnClickListener { _, _ ->

                })
            builder.create().show()
        }
    }


    /*        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { _, bundle ->
            val news = bundle.getSerializable("news") as News
            adapter.addItem(news)
            binding.recyclerview.adapter = adapter
        }*/
}