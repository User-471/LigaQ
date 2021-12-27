package com.ligasportquiz.freeforyou.bestplayer.menu.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.game.question.QuestionAdapter
import com.ligasportquiz.freeforyou.bestplayer.menu.game.utils.getQuestions
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, GameActivity::class.java)
        }
    }

    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initViewPager()
    }

    private fun initViewPager() {
        questionAdapter = QuestionAdapter(getQuestions()) {
            if (viewpager.currentItem < 4) {
                viewpager.currentItem = viewpager.currentItem + 1
            }
            else {
                showPlayAgainDialog()
            }
        }
        viewpager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewpager?.adapter = questionAdapter

        viewpager?.isUserInputEnabled = false
    }

    private fun showPlayAgainDialog() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.finish_congrats))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                dialog.dismiss()
                finish()
                startActivity(newIntent(this))
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .show()
    }
}