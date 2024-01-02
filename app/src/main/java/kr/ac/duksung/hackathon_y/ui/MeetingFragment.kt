package kr.ac.duksung.hackathon_y.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.databinding.FragmentMeetingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MeetingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeetingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewBinding: FragmentMeetingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentMeetingBinding.inflate(inflater, container,false)
        viewBinding.actionbar.tvTitle.visibility = View.VISIBLE

        return inflater.inflate(R.layout.fragment_meeting, container, false)
    }

}