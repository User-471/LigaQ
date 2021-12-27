package com.ligasportquiz.freeforyou.bestplayer.menu.game.utils

import com.ligasportquiz.freeforyou.bestplayer.R
import com.ligasportquiz.freeforyou.bestplayer.menu.model.LetterModel
import com.ligasportquiz.freeforyou.bestplayer.menu.model.Question

fun getLetters(): ArrayList<String> {
    return arrayListOf("а", "б","в","г","д","е","ё","ж","з","и","й","к","л","м","н","о","п","р","с","т","у","ф","х","ц","ч","ш","щ",
        "ъ","ы","ь","э","ю","я")
}

fun getLettersByCount(count: Int): ArrayList<String> {
    val letters = arrayListOf<String>()
    for (i in 0 until count) {
        letters.add(getLetters().random())
    }

    return letters
}

fun getEmptyLettersByCount(count: Int): ArrayList<LetterModel> {
    val letters = arrayListOf<LetterModel>()
    for (i in 0 until count) {
        letters.add(LetterModel(999, ""))
    }

    return letters
}

fun getQuestions(): ArrayList<Question> {
    val questions = arrayListOf<Question>()

    questions.add(
        Question(
            0,
            R.drawable.sport1,
            "футбол"
        )
    )

    questions.add(
        Question(
            1,
            R.drawable.sport2,
            "баскетболл"
        )
    )

    questions.add(
        Question(
            2,
            R.drawable.sport3,
            "хоккей"
        )
    )

    questions.add(
        Question(
            3,
            R.drawable.sport4,
            "волейбол"
        )
    )

    questions.add(
        Question(
            4,
            R.drawable.sport5,
            "регби"
        )
    )

    return questions
}