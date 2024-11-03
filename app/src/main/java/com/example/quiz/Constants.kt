package com.example.quiz

object Constants {

    fun getQuestions() : ArrayList<Questions>{

        val questionsList = ArrayList<Questions> ()

        val q1 = Questions(1, "What Country flag is this?",
            R.drawable.canada,
            "Canada", "Brazil",
            "USA", "México",
            1)

        val q2 = Questions(2, "What Country flag is this?",
            R.drawable.somalia,
            "Kenya", "Somalia",
            "Lebanon", "Vietnam",
            2)

        val q3 = Questions(3, "What Country flag is this?",
            R.drawable.japao,
            "South Korea", "China",
            "Japan", "Thailand",
            3)

        val q4 = Questions(4, "What Country flag is this?",
            R.drawable.australia,
            "New Zealand", "Australia",
            "UK", "South Africa",
            2)

        val q5 = Questions(5, "What Country flag is this?",
            R.drawable.butao,
            "Nepal", "Bhutan",
            "Bangladesh", "Sri Lanka",
            2)

        val q6 = Questions(6, "What Country flag is this?",
            R.drawable.malawi,
            "Zambia", "Mozambique",
            "Malawi", "Tanzania",
            3)

        val q7 = Questions(7, "What Country flag is this?",
            R.drawable.moldavia,
            "Romania", "Moldova",
            "Ukraine", "Belarus",
            2)

        val q8 = Questions(8, "What Country flag is this?",
            R.drawable.brunei,
            "Malaysia", "Brunei",
            "Singapore", "Indonesia",
            2)

        val q9 = Questions(9, "What Country flag is this?",
            R.drawable.portugal,
            "Portugal", "Spain",
            "Italy", "France",
            1)

        val q10 = Questions(10, "What Country flag is this?",
            R.drawable.grecia,
            "Turkey", "Cyprus",
            "Greece", "Malta",
            3)




        questionsList.add(q1)
        questionsList.add(q2)
        questionsList.add(q3)
        questionsList.add(q4)
        questionsList.add(q5)
        questionsList.add(q6)
        questionsList.add(q7)
        questionsList.add(q8)
        questionsList.add(q9)
        questionsList.add(q10)


        return questionsList
    }

}