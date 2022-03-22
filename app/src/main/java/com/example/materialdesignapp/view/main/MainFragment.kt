package com.example.materialdesignapp.view.main


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
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentMainBinding
import com.example.materialdesignapp.utils.BindingFragment
import com.example.materialdesignapp.view.MainActivity
import com.example.materialdesignapp.view.settings.SettingsFragment
import com.example.materialdesignapp.viewmodel.PictureOfTheDayAppState
import com.example.materialdesignapp.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    lateinit var day: String
    var isMainScreen = true
    var isMain = true

    override val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        //  day= SimpleDateFormat("yyyy-MM-dd").format(Date())
        //  viewModel.sendRequest(day)

        getPictureOfTheDay(0)

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        bottomSheetCreate()

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)

        changeScreen()

        chipChoise()

    }

    fun bottomSheetCreate() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.maxHeight = 5000
        bottomSheetBehavior.peekHeight = 500
//        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    //  BottomSheetBehavior.STATE_DRAGGING -> showSnackBarWithoutAction("Drag")
                    //  BottomSheetBehavior.STATE_COLLAPSED -> showSnackBarWithoutAction("collapse")
                    BottomSheetBehavior.STATE_EXPANDED -> showSnackBarWithoutAction("Open!")
                    //  BottomSheetBehavior.STATE_HALF_EXPANDED -> showSnackBarWithoutAction("On Half")
                    BottomSheetBehavior.STATE_HIDDEN -> showSnackBarWithoutAction("Hide!")
                    //  BottomSheetBehavior.STATE_SETTLING -> showSnackBarWithoutAction("settling")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("mylogs", "slideOffset $slideOffset")
            }
        })

    }

    fun getPictureOfTheDay(daysDiff: Int) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysDiff)

        day = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
        viewModel.sendRequest(day)

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
                binding.imageView.load(pictureOfTheDay.serverResponse.url) {
                    placeholder(R.drawable.ic_no_photo_vector)
                }
                binding.included.bottomSheetDescriptionHeader.text =
                    pictureOfTheDay.serverResponse.title
                binding.included.bottomSheetDescription.text =
                    pictureOfTheDay.serverResponse.explanation
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
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
                // Toast.makeText(requireContext(), "app_bar_settings", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(
                    requireActivity().supportFragmentManager,
                    "tag"
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun changeScreen() {
        binding.fab.setOnClickListener {
            if (isMainScreen) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageResource(R.drawable.ic_back_fab)
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_hamburger_menu_bottom_bar
                )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageResource(R.drawable.ic_plus_fab)
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMainScreen = !isMainScreen
        }

    }

    fun chipChoise() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            // val idCheck=  binding.chipGroup.findViewById<Chip>(checkedId)?.let { it ->
            when (checkedId) {
                1 -> {
                    getPictureOfTheDay(2)
                }
                2 -> {
                    getPictureOfTheDay(1)
                }
                3 -> {
                    getPictureOfTheDay(0)
                }
            }
            //  Toast.makeText(requireContext(), "${checkedId}", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}