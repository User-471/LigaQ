package com.ligasportquiz.freeforyou.bestplayer.menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.game.GameActivity
import kotlinx.android.synthetic.main.activity_exam.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        ib_new_game?.setOnClickListener {
            startActivity(GameActivity.newIntent(this))
        }
        ib_leaders?.setOnClickListener {
            Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
        }
        ib_exit?.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage(getString(R.string.exit_alert))
                .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                    finish()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}