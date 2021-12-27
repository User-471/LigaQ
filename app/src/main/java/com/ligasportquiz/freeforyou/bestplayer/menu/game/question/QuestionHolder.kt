package com.ligasportquiz.freeforyou.bestplayer.menu.game.question

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.game.answer.AnswerAdapter
import com.ligasportquiz.freeforyou.bestplayer.menu.game.letter.LetterAdapter
import com.ligasportquiz.freeforyou.bestplayer.menu.game.utils.getEmptyLettersByCount
import com.ligasportquiz.freeforyou.bestplayer.menu.game.utils.getLettersByCount
import com.ligasportquiz.freeforyou.bestplayer.menu.model.LetterModel
import com.ligasportquiz.freeforyou.bestplayer.menu.model.Question
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val lettersAdapter =
        LetterAdapter(arrayListOf(), arrayListOf()) { onChooseLetterClicked(it) }
    private val answersLettersAdapter = AnswerAdapter(arrayListOf()) { }

    private lateinit var question: Question
    private lateinit var questionListener: (Question) -> Unit

    fun bind(
        data: Question,
        onQuestionAnswered: (Question) -> Unit
    ) {
        question = data
        questionListener = onQuestionAnswered
        itemView.iv_sport.setImageResource(question.image)

        val spanCount = if(question.rightAnswer.length > 8) 8 else question.rightAnswer.length

        itemView.rv_choose_letters?.layoutManager =
            GridLayoutManager(itemView.context, spanCount)
        itemView.rv_choose_letters?.adapter = lettersAdapter

        itemView.rv_answer_letters?.layoutManager =
            GridLayoutManager(itemView.context, spanCount)
        itemView.rv_answer_letters?.adapter = answersLettersAdapter

        val rightLetters = ArrayList(question.rightAnswer.split("")).filter { it.isNotEmpty() }

        val allLetters =
            ArrayList(ArrayList(rightLetters + getLettersByCount(question.rightAnswer.length)).shuffled())

        val allLetterModels = arrayListOf<LetterModel>()

        for (i in allLetters.indices) {
            allLetterModels.add(LetterModel(i, allLetters[i]))
        }

        val allLettersModelsOriginal = arrayListOf<LetterModel>()

        for (i in allLetterModels) {
            allLettersModelsOriginal.add(LetterModel(i.id, i.letter))
        }

        lettersAdapter.updateData(allLetterModels)
        lettersAdapter.updateOriginalData(allLettersModelsOriginal)
        answersLettersAdapter.updateData(getEmptyLettersByCount(question.rightAnswer.length))

        itemView.ib_remove?.setOnClickListener { onRemoveLetterClicked() }
        itemView.ib_refresh?.setOnClickListener { onClearClicked() }
    }

    private fun onChooseLetterClicked(letterModel: LetterModel) {
        if (!answersLettersAdapter.checkIfWordIsFull()) {
            if(letterModel.letter.isNotEmpty()) {
                answersLettersAdapter.addLetter(letterModel)
                lettersAdapter.popLetter(letterModel)

                if (answersLettersAdapter.getLastLetter().letter.isNotEmpty()) {
                    checkIfAnswerIsRight()
                }
            }
        }
        else {
            checkIfAnswerIsRight()
        }
    }

    private fun checkIfAnswerIsRight() {
        if (question.rightAnswer == answersLettersAdapter.getCurrentAnswer()) {
            Toast.makeText(itemView.context, itemView.context.getString(R.string.well_done), Toast.LENGTH_SHORT).show()
            questionListener(question)
        }
        else {
            Toast.makeText(itemView.context, itemView.context.getString(R.string.bad_done), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onRemoveLetterClicked() {
        if (!answersLettersAdapter.checkIfWordIsEmpty()) {
            if(answersLettersAdapter.getLastVisibleLetter() != null) {
                lettersAdapter.insertBackLetter(answersLettersAdapter.getLastVisibleLetter()!!)
            }
            answersLettersAdapter.dropLastLetter()
        }
    }

    private fun onClearClicked() {
        lettersAdapter.restore()
        answersLettersAdapter.clearList()
        answersLettersAdapter.updateData(getEmptyLettersByCount(question.rightAnswer.length))
    }
}