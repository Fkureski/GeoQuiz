package com.example.quiz

object Constants {

    fun getQuestions() : ArrayList<Questions> {

        val questionsList = ArrayList<Questions> ()

        // Pergunta 1 - Bandeira
        val q1 = Questions(
            1, "Qual bandeira de país é esta?",
            R.drawable.canada,
            "Canadá", "Brasil",
            "EUA", "México",
            1
        )

        // Pergunta 2 - Bandeira
        val q2 = Questions(
            2, "Qual bandeira de país é esta?",
            R.drawable.japao,
            "Coreia do Sul", "China",
            "Japão", "Tailândia",
            3
        )

        // Pergunta 3 - Bandeira
        val q3 = Questions(
            3, "Qual bandeira de país é esta?",
            R.drawable.portugal,
            "Portugal", "Espanha",
            "Itália", "França",
            1
        )

        // Pergunta 4 - Capital
        val q4 = Questions(
            4, "Qual é a capital do Japão?",
            0,
            "Pequim", "Seul",
            "Tóquio", "Osaka",
            3
        )

        // Pergunta 5 - Capital
        val q5 = Questions(
            5, "Qual é a capital da Austrália?",
            0,  // Sem imagem
            "Sydney", "Melbourne",
            "Canberra", "Brisbane",
            3
        )

        // Pergunta 6 - Continente
        val q6 = Questions(
            6, "Em qual continente está localizada a Somália?",
            0,
            "Ásia", "África",
            "América do Norte", "Europa",
            2
        )

        // Pergunta 7 - Capital
        val q7 = Questions(
            7, "Qual é a capital do Canadá?",
            0,
            "Toronto", "Vancouver",
            "Montreal", "Ottawa",
            4
        )

        // Pergunta 8 - Continente
        val q8 = Questions(
            8, "Em qual continente está localizada a Argentina?",
            0,
            "América do Norte", "América do Sul",
            "África", "Europa",
            2
        )

        // Pergunta 9 - Capital
        val q9 = Questions(
            9, "Qual é a capital da França?",
            0,
            "Roma", "Paris",
            "Londres", "Berlim",
            2
        )

        // Pergunta 10 - Capital
        val q10 = Questions(
            10, "Qual é a capital da Alemanha?",
            0,
            "Viena", "Berlim",
            "Munique", "Hamburgo",
            2
        )

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
