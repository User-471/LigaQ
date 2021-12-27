package com.ligasportquiz.freeforyou.bestplayer.menu.game.letter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ligasportquiz.freeforyou.bestplayer.menu.model.LetterModel
import kotlinx.android.synthetic.main.item_choose_letter.view.*

class LetterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: LetterModel,
             onItemClickListener: (LetterModel) -> Unit) {
        itemView.setOnClickListener { onItemClickListener(data) }
        itemView.tv_letter?.text = data.letter
    }
}