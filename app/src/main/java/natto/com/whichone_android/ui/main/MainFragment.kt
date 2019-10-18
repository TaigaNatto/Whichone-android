package natto.com.whichone_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import natto.com.whichone_android.R
import natto.com.whichone_android.databinding.FragmentMainBinding
import natto.com.whichone_android.domain.User
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        ) as FragmentMainBinding

        viewModel.userLiveData.observe(
            this.viewLifecycleOwner,
            Observer {
                binding.logTextView.text = it.name
            }
        )

        binding.logTextView.text = "This is log-textview"

        binding.sendBtn.setOnClickListener {
            val sampleUser = User(
                id = 0,
                name = "sample"
            )
            viewModel.addUser(sampleUser)
        }

        return binding.root
    }
}