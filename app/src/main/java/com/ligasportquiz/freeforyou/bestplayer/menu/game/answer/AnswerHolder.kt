package com.ligasportquiz.freeforyou.bestplayer.menu.game.answer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ligasportquiz.freeforyou.bestplayer.menu.model.LetterModel
import kotlinx.android.synthetic.main.item_answer_letter.view.*

class AnswerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: LetterModel,
             onItemClickListener: (LetterModel) -> Unit) {
        itemView.setOnClickListener { onItemClickListener(data) }
        itemView.tv_answer_letter?.text = data.letter
    }
}