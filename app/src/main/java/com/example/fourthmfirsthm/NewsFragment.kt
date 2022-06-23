package com.example.fourthmfirsthm

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.fourthmfirsthm.databinding.ActivityMainBinding
import com.example.fourthmfirsthm.databinding.FragmentNewsBinding
import com.example.fourthmfirsthm.model.News
import java.text.SimpleDateFormat
import java.util.*


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = arguments?.getString("key")
        binding.editText.setText(text)

        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val text = binding.editText.text.toString()
        val news = News(0, text, System.currentTimeMillis())
        App.database.newsDao().insert(news)
        findNavController().navigateUp()
    }


/*        val bundle = bundleOf("news" to news)
        parentFragmentManager.setFragmentResult("rk_news",bundle)*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}