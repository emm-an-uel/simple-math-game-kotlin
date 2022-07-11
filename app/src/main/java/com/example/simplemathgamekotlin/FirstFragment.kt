package com.example.simplemathgamekotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.simplemathgamekotlin.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    // DECLARING VARIABLES
    val random = java.util.Random()
    var A = random.nextInt(15)
    var B = random.nextInt(15)
    var solution = A+B
    var totalQuestions = 0
    var score = 0

    private fun displayQuestions(view:View) {
        val question = getString(R.string.question, A, B, "?")
        view.findViewById<TextView>(R.id.textview_first).text = question

        val scoreValue = getString(R.string.string_score, score, totalQuestions)
        view.findViewById<TextView>(R.id.score).text = scoreValue
    }

    private fun newQuestions(view:View) {
        A = random.nextInt(15)
        B = random.nextInt(15)
        solution = A+B
        totalQuestions++ // adds 1 to var totalQuestions
        view.findViewById<EditText>(R.id.userAnswer).setText("") // reset to blank

        // DISPLAY NEW QUESTION
        displayQuestions(view)
    }

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DISPLAY FIRST QUESTION AND numQuestions, score
        displayQuestions(view)

        // CHECK ANSWER
        view.findViewById<Button>(R.id.button_check).setOnClickListener() {
            val userAnswer = view.findViewById<EditText>(R.id.userAnswer)
            if (userAnswer.text.toString() != "") { // if not empty
                val userAnswerString = userAnswer.text.toString()
                val userAnswerInt = userAnswerString.toInt()

                if (userAnswerInt == solution) {
                    val toastCheck = Toast.makeText(context, "Correct", Toast.LENGTH_SHORT)
                    toastCheck.show()
                    score++ // adds 1 to var score
                }

                else {
                    val toastCheck = Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT)
                    toastCheck.show()
                }

                newQuestions(view)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}