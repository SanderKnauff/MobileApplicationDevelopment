package ooo.sansk.swipequiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question_view.view.*

class QuestionAdapter(private val questions: List<QuizQuestion>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    lateinit var context: Context;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.question_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quizQuestion: QuizQuestion) {
            itemView.questionText.text = quizQuestion.question
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, quizQuestion.correctAnswer.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}