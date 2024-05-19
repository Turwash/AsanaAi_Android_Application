package com.example.android.getfit

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.navigation.findNavController
import com.example.android.getfit.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    private val description: String = "<p><br>Our app leverages advanced machine learning techniques to count pushups and squats using your phone's camera, providing a seamless and efficient way to track your fitness progress. The app stores your exercise data in a database, allowing you to view detailed histories of your workouts.<br><br><b>Machine Learning Integration</b><br>The app utilizes two key domains of machine learning: Pose Estimation and K-means Clustering.<br><br><b>Pose Estimation</b><br>Pose Estimation is employed to accurately estimate the key points of the human body and track their movements during exercises. This ensures precise counting of pushups and squats by analyzing the positions and motions of your body in real time.<br><br><b>K-means Clustering</b><br>K-means clustering, an unsupervised machine learning algorithm, is used to analyze the dataset and identify patterns within it. By grouping similar data together into clusters, the model can differentiate between different exercises and repetitions.<br><br>Our app’s K-means clustering model was trained on a diverse dataset containing hundreds of images of pushups and squats taken from various angles. This extensive training helps the model generalize well, ensuring accurate exercise counting regardless of the user's position or camera angle.By combining these advanced machine learning techniques, our app provides reliable and detailed tracking of your pushups and squats, helping you stay on top of your fitness goals.<p>"
    private val title: String = "ML-Powered Fitness Tracking App"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        binding = FragmentInfoBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            var isToolbarShown = false

            // scroll change listener begins at Y = 0 when image is fully collapsed
            aicameraDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    // User scrolled past image to height of toolbar and the title text is
                    // underneath the toolbar, so the toolbar should be shown.
                    val shouldShowToolbar = scrollY > toolbar.height

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
                        appbar.isActivated = shouldShowToolbar

                        // Show the plant name if toolbar is shown
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            setHasOptionsMenu(true)
            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            aicameraDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(description)
            }

            aicameraDetailName.text = title

        }



        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

}