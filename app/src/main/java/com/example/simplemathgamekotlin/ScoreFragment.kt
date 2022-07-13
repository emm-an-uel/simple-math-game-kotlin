package com.example.simplemathgamekotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
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

        val wrongAnswersString = args.wrongAnswers
        val wrongAnswersList: List<String> = wrongAnswersString.split("-").toList()

        // for each string in wrongAnswersList, print into its own line in fragment_score.xml
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)
        for (string in wrongAnswersList) {
            // create a new TextView and fill it with var "string"
            val textView = TextView(context)
            textView.text = string
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            linearLayout.addView(textView)
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