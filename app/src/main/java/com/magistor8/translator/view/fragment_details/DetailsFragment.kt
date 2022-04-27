package com.magistor8.translator.view.fragment_details

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import coil.load
import com.magistor8.translator.R
import com.magistor8.translator.databinding.FragmentDetailsBinding
import com.magistor8.translator.domain.entities.DataModel
import com.magistor8.translator.domain.entities.Meanings
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


private const val BUNDLE_DETAILS = "BUNDLE_DETAILS"

class DetailsFragment : Fragment(){

    private val viewModel: DetailsFragmentViewModel by viewModel(named("DetailsFragment"))

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            loadData(it)
        }
    }

    private fun loadData(bundle: Bundle) {
        val data = bundle.getParcelable<DataModel>(BUNDLE_DETAILS)
        val set = ConstraintSet()
        data?.let {
            binding.search.text = data.text
            data.meanings?.let {
                var num = 1
                var lastId = 0
                for (meanings in it) {
                    val view = LayoutInflater.from(context).inflate(R.layout.meanings, null)
                    view.id = View.generateViewId()
                    binding.constraint.addView(view)
                    set.clone(binding.constraint)
                    if (num == 1) {
                        set.connect(view.id, ConstraintSet.TOP, R.id.search, ConstraintSet.BOTTOM)
                    } else {
                        set.connect(view.id, ConstraintSet.TOP, lastId, ConstraintSet.BOTTOM)
                    }
                    set.connect(view.id, ConstraintSet.START, binding.constraint.id, ConstraintSet.START)
                    set.connect(view.id, ConstraintSet.END, binding.constraint.id, ConstraintSet.END)
                    set.applyTo(binding.constraint)
                    setValues(view, meanings)
                    lastId = view.id
                    num++
                }
            }
        }
    }

    private fun setValues(view: View, meanings: Meanings) {
        //Image
        if (meanings.imageUrl !== null) {
            val normalizeUrl = "https:" + meanings.imageUrl
            view.findViewById<AppCompatImageView>(R.id.image).load(normalizeUrl)
        }
        //Translation
        if (meanings.translation !== null && meanings.translation.translation !== null) {
            view.findViewById<TextView>(R.id.translation).text = meanings.translation.translation
        } else {
            view.findViewById<TextView>(R.id.translation).text = R.string.clearText.toString()
        }
        //Audio
        if (meanings.soundUrl !== null) {
            view.findViewById<AppCompatButton>(R.id.play).setOnClickListener {
                playAudio(meanings.soundUrl)
            }
        } else {
            view.findViewById<AppCompatButton>(R.id.play).visibility = GONE
        }
    }

    private fun playAudio(url: String) {
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare()
            start()
        }
    }




}