package ooo.sansk.swipequiz

data class QuizQuestion(val question: String, val explanation: String, val correctAnswer: Boolean)

val QUIZ_QUESTIONS = listOf(
    QuizQuestion("Caterpie evolves into Metapod", "Caterpie evolves into Metapod, then into Butterfree", true),
    QuizQuestion("There are 9 certified Pokémon League Badges", "There are only 8", false),
    QuizQuestion("Poliwag evolves 3 times", "Poliwag evolves only twice; first into Poliwhirl, then into Poliwrath or Politoed", false),
    QuizQuestion("Thunder moves are effective against ground element-type Pokémon", "It's the other way around", false),
    QuizQuestion("Pokémon of the same kind and level are not identical", "They are not", true),
    QuizQuestion("TM28 contains Rock Smash", "TM28 contains dig", false)
)