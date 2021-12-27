package com.ligasportquiz.freeforyou.bestplayer.menu.game.letter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.model.LetterModel

class LetterAdapter(
    var originalList: ArrayList<LetterModel>,
    var list: ArrayList<LetterModel>,
    private val onItemClickListener: (LetterModel) -> Unit
) : RecyclerView.Adapter<LetterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_choose_letter, parent, false)
        return LetterHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: LetterHolder, position: Int) {
        holder.bind(
            list[position],
            onItemClickListener
        )
    }

    override fun getItemCount(): Int = list.size

    fun updateData(list: ArrayList<LetterModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun updateOriginalData(list: ArrayList<LetterModel>) {
        this.originalList = list
    }

    fun insertBackLetter(letterModel: LetterModel) {

        for (i in list) {
            if (i.id == letterModel.id) {
                i.letter = letterModel.letter
                notifyDataSetChanged()
                return
            }
        }
    }

    fun popLetter(letterModel: LetterModel) {
        if (letterModel.letter.isNotEmpty()) {
            for (i in list) {
                if(i.id == letterModel.id) {
                    i.letter = ""
                }
            }
        }

        notifyDataSetChanged()
    }

    fun restore() {
        this.list = arrayListOf()

        val refreshedList = arrayListOf<LetterModel>()
        for (i in originalList) {
            this.list.add(LetterModel(i.id, i.letter))
        }

        notifyDataSetChanged()
    }
}