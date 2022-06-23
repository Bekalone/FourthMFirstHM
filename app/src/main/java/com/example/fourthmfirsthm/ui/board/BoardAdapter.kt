package com.example.fourthmfirsthm.ui.board

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthmfirsthm.Prefs
import com.example.fourthmfirsthm.databinding.ItemBoardBinding
import com.example.fourthmfirsthm.model.Board

class BoardAdapter(val context: Context, val navController: NavController) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    private val data = arrayListOf<Board>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
    fun addItem(board: Board) {
        data.add(board)
    }

    inner class BoardViewHolder(private var binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board) {
            binding.animationView.setAnimation(board.lottie)
            binding.textTitle.text = board.title
            binding.textDesc.text = board.description

            if (data.lastIndexOf(board) == data.lastIndex) {
                binding.btnStart.visibility = View.VISIBLE
                binding.wormDotsIndicator.visibility = View.INVISIBLE
            } else {
                binding.btnStart.visibility = View.INVISIBLE
                binding.wormDotsIndicator.visibility = View.VISIBLE
            }
            binding.btnStart.setOnClickListener {
                Prefs(context).saveState()
                navController.navigateUp()
            }
        }

    }
}
