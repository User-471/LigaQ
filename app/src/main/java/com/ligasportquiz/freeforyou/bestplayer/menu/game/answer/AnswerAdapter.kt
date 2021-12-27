package com.ligasportquiz.freeforyou.bestplayer.menu.game.answer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.model.LetterModel

class AnswerAdapter(
    var list: ArrayList<LetterModel>,
    private val onItemClickListener: (LetterModel) -> Unit
) : RecyclerView.Adapter<AnswerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_answer_letter, parent, false)
        return AnswerHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
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

    fun addLetter(letter: LetterModel) {
        for (i in list.indices) {
            if (list[i].letter.isEmpty()) {
                list[i].id = letter.id
                list[i].letter = letter.letter
                notifyDataSetChanged()
                return
            }
        }
    }

    fun dropLastLetter() {
        for (i in list.indices.reversed()) {
            if (list[i].letter.isNotEmpty()) {
                list[i].id = 999
                list[i].letter = ""
                notifyDataSetChanged()
                return
            }
        }
    }

    fun getCurrentAnswer(): String {
        val stringBuilder = StringBuilder()

        for (i in list) {
            stringBuilder.append(i.letter)
        }

        return stringBuilder.toString()
    }

    fun getLastVisibleLetter(): LetterModel? {

        for (i in list.reversed()) {
            if(i.letter.isNotEmpty()) {
                return i
            }
        }

        return null
    }

    fun getLastLetter(): LetterModel {
        return list.last()
    }

    fun checkIfWordIsEmpty(): Boolean {
        for (i in list) {
            if (i.letter.isNotEmpty()) {
                return false
            }
        }

        return true
    }

    fun checkIfWordIsFull(): Boolean {
        for (i in list) {
            if (i.letter.isEmpty()) {
                return false
            }
        }

        return true
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }
}