package com.example.fourthmfirsthm.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthmfirsthm.databinding.ItemNewsBinding
import com.example.fourthmfirsthm.model.News
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.TemporalAdjuster
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.days
import kotlin.time.toDuration

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var onClickListener: ((News) -> Unit?)? = null
    var onLongClickListener: ((Int) -> Unit?)? = null
    private var data = arrayListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position])
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.GRAY)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addItem(news: News?) {
        news?.let {
            data.add(0, it)
            notifyItemInserted(data.lastIndexOf(news))
        }

    }

    fun getItem(position: Int): News {
        return data[position]
    }

    fun getData(): ArrayList<News> {
        return data
    }

    fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItems(list: ArrayList<News>) {
        this.data = list
        notifyDataSetChanged()

    }


    inner class NewsViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            val simpleDateFormat = SimpleDateFormat("HH:mm, dd MMMM yyyy")
            val dataTime = Date(news.createdAt)
            val time: String = simpleDateFormat.format(dataTime)
            binding.textTitle.text = news.title
            binding.textData.text = time
            itemView.setOnClickListener {
                onClickListener?.invoke(news)
            }
            itemView.setOnLongClickListener {
                onLongClickListener?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }
}
