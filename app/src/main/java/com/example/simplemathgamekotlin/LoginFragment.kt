package com.example.simplemathgamekotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simplemathgamekotlin.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    // DECLARING VARIABLES
    var username = ""

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonPlayGame).setOnClickListener() {
            username = view.findViewById<EditText>(R.id.username).text.toString()

            if (username == "") {
                val myToast = Toast.makeText(context, "Enter username!", Toast.LENGTH_SHORT)
                myToast.show()
            }

            else {
                val action = LoginFragmentDirections.actionLoginFragmentToGameFragment(username)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}