package com.ligasportquiz.freeforyou.bestplayer.menu.game.question

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.model.Question

class QuestionAdapter(
    var list: ArrayList<Question>,
    private val onEndReached: (Question) -> Unit
) : RecyclerView.Adapter<QuestionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        holder.bind(
            list[position],
            onEndReached
        )
    }

    override fun getItemCount(): Int = list.size

    fun updateData(list: ArrayList<Question>) {
        this.list = list
        notifyDataSetChanged()
    }
}