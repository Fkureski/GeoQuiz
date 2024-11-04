package com.example.quiz.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment() {
    
    lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)

        binding.startBtn.setOnClickListener { view: View ->
            if(binding.etName.text !!.isNotEmpty()){
                view.findNavController().navigate(R.id.action_homeScreen_to_gameScreen)
            } else {
                Toast.makeText(context, "Por favor, coloque um nome", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}