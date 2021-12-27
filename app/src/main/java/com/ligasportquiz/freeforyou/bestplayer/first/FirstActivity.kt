package com.ligasportquiz.freeforyou.bestplayer.first

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isClientError
import com.github.kittinunf.fuel.core.isServerError
import com.github.kittinunf.fuel.core.isSuccessful
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.additional.AdditionalActivity
import com.ligasportquiz.freeforyou.bestplayer.extra.getLastUrl
import com.ligasportquiz.freeforyou.bestplayer.extra.saveLastUrl
import com.ligasportquiz.freeforyou.bestplayer.menu.MenuActivity
import kotlinx.android.synthetic.main.activity_first.*
import timber.log.Timber

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        getInfo()
    }

    private fun getInfo() {
        Fuel.get("https://bestterto.ru/pharaon")
            .response { request, response, result ->

                Timber.d("TAG_RESP_5: ${Gson().toJson(response)}")

                pb_loading.visibility = View.GONE

                if (response.isSuccessful) {

                    val resp = response.body().asString("application/json; charset=utf-8")

                    Timber.d("TAG_RESP_1: $resp")

                    if (!resp.isNullOrEmpty()) {

                        Timber.d("TAG_RESP_2")

                        if (getLastUrl(this).isNullOrEmpty() || getLastUrl(this) == "https://google.com") {
                            saveLastUrl(this, resp)
                            Timber.d("TAG_RESP_3")
                        }

                        goToAdditionalActivity()
                    } else {
                        goToExamActivity()
                    }
                }

                if (response.isServerError || response.isClientError) {
                    val responseObjectType = object : TypeToken<Error>() {}.type
                    val responseObject = Gson().fromJson(
                        response.body().asString("application/json; charset=utf-8"),
                        responseObjectType
                    ) as Error

                    Timber.d("TAG_S_6_ERROR: ${responseObject.toString()}")

                    goToExamActivity()
                }
            }
    }

    private fun goToExamActivity() {
        finishAffinity()
        val myIntent = Intent(this, MenuActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(0, 0)
    }

    private fun goToAdditionalActivity() {
        finishAffinity()
        val myIntent = Intent(this, AdditionalActivity::class.java)
        startActivity(myIntent)
        overridePendingTransition(0, 0)
    }
}