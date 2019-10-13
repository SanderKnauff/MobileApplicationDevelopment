package ooo.sansk.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizQuestions = QUIZ_QUESTIONS.toMutableList()
    private val questionAdapter = QuestionAdapter(quizQuestions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        rvQuestions.adapter = questionAdapter
        rvQuestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        questionAdapter.notifyDataSetChanged()
        ItemTouchHelper(TouchHandler()).attachToRecyclerView(rvQuestions)
    }

    inner class TouchHandler: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)){

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            checkAnswer(quizQuestions[viewHolder.adapterPosition], direction == ItemTouchHelper.RIGHT)
            questionAdapter.notifyDataSetChanged()
        }

    }

    private fun checkAnswer(quizQuestion: QuizQuestion, b: Boolean) {
        //Both true === true and false == false should equal to true.
        // Kotlin does not seems to have a native XNOR, however this should do the trick
        if(b.xor(quizQuestion.correctAnswer).not()) {
            Snackbar.make(rvQuestions, getString(R.string.correct, quizQuestion.explanation), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(rvQuestions, getString(R.string.incorrect, quizQuestion.explanation), Snackbar.LENGTH_SHORT).show()
        }
    }

}
