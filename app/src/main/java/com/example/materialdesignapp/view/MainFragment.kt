package com.example.materialdesignapp.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentMainBinding
import com.example.materialdesignapp.utils.BindingFragment
import com.example.materialdesignapp.viewmodel.PictureOfTheDayAppState
import com.example.materialdesignapp.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data  = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun renderData(pictureOfTheDay: PictureOfTheDayAppState) {
        when (pictureOfTheDay) {
            is PictureOfTheDayAppState.Error -> {
                Snackbar.make(binding.imageView, pictureOfTheDay.error, Snackbar.LENGTH_LONG).show()
            }
            is PictureOfTheDayAppState.Loading -> {

            }
            is PictureOfTheDayAppState.Success -> {
                binding.imageView.load(pictureOfTheDay.serverResponse.url){
                    placeholder(R.drawable.ic_no_photo_vector)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =  MainFragment()
    }
}