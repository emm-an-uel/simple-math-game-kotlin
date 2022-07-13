package com.example.simplemathgamekotlin

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simplemathgamekotlin.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root

    }

    val args: ScoreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = args.username
        val questionNumber = args.questionNumber
        val score = args.score
        val currentScore = getString(R.string.results, username, score, questionNumber)
        view.findViewById<TextView>(R.id.highscore_table).text = currentScore

        if (questionNumber == 0) { // if no questions answered
            view.findViewById<TextView>(R.id.textView_wrongList).text = "Should've tried some questions"
        }

        else { // if some questions answered

            val wrongQuestionsString = args.wrongQuestions
            val correctAnswersString = args.correctAnswers

            if (correctAnswersString == "") { // if no wrong answers
                view.findViewById<TextView>(R.id.textView_wrongList).text = "Congratulations!" // set text to "Congratulations!"
            }

            else { // if there are wrong answers
                val wrongQuestionsList: List<String> = wrongQuestionsString.split("-").toList()
                val correctAnswersList: List<String> = correctAnswersString.split("-").toList()
                val numOfMistakes = wrongQuestionsList.size

                // for each string in wrongAnswersList, print into its own line in fragment_score.xml
                val linearLayoutQuestions = view.findViewById<LinearLayout>(R.id.linearLayoutQuestions)
                val linearLayoutSolutions = view.findViewById<LinearLayout>(R.id.linearLayoutSolutions)

                var i = 0
                while (i < numOfMistakes) {
                    val question = wrongQuestionsList[i] // declaring values
                    val answer = correctAnswersList[i]

                    val textViewQuestion = TextView(context) // new textviews
                    val textViewAnswer = TextView(context)

                    textViewQuestion.text = question
                    textViewAnswer.text = answer

                    textViewQuestion.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    textViewAnswer.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                    textViewAnswer.setTextColor(getResources().getColor(R.color.pastel_orange, getResources().newTheme()));
                    textViewAnswer.setPadding(100,0,0,0)

                    linearLayoutQuestions.addView(textViewQuestion)
                    linearLayoutSolutions.addView(textViewAnswer)

                    i++
                }
            }
        }

        view.findViewById<Button>(R.id.button_goto_first_fragment).setOnClickListener {
            val action = ScoreFragmentDirections.actionSecondFragmentToFirstFragment(username)
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_goto_login_fragment).setOnClickListener {
            findNavController().navigate(R.id.action_ScoreFragment_to_LoginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}