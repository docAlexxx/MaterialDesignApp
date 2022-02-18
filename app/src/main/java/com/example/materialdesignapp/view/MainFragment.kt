package com.example.materialdesignapp.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentMainBinding
import com.example.materialdesignapp.utils.BindingFragment
import com.example.materialdesignapp.viewmodel.PictureOfTheDayAppState
import com.example.materialdesignapp.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

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

        bottomSheetCreate()

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    fun bottomSheetCreate(){
        bottomSheetBehavior= BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.maxHeight=5000
        bottomSheetBehavior.peekHeight=200
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        bottomSheetBehavior.addBottomSheetCallback( object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState){
                    BottomSheetBehavior.STATE_DRAGGING -> showSnackBarWithoutAction("Drag")
                    BottomSheetBehavior.STATE_COLLAPSED -> showSnackBarWithoutAction("collapse")
                    BottomSheetBehavior.STATE_EXPANDED -> showSnackBarWithoutAction("Open!")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> showSnackBarWithoutAction("On Half")
                    BottomSheetBehavior.STATE_HIDDEN -> showSnackBarWithoutAction("Hide!")
                    BottomSheetBehavior.STATE_SETTLING -> showSnackBarWithoutAction("settling")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("mylogs", "slideOffset $slideOffset")
            }

        })

    }

    private fun showSnackBarWithoutAction(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT).show()
            }
            R.id.app_bar_settings -> {
                Toast.makeText(requireContext(), "app_bar_settings", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager,"tag")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        @JvmStatic
        fun newInstance() =  MainFragment()
    }
}