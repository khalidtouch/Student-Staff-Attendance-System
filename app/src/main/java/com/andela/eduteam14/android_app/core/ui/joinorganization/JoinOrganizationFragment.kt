package com.andela.eduteam14.android_app.core.ui.joinorganization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.auth.AuthActivity
import com.andela.eduteam14.android_app.core.ui.extensions.ShowToast
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.search.SearchActivity
import com.andela.eduteam14.android_app.databinding.FragmentJoinOrganizationBinding
import com.andela.eduteam14.android_app.databinding.FragmentRegisterBinding

class JoinOrganizationFragment : Fragment(), UiAction {

    private var _binding: FragmentJoinOrganizationBinding? = null

    private val binding get() = _binding

    private lateinit var searchBar: LinearLayout
    private lateinit var join: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJoinOrganizationBinding.inflate(inflater, container, false)

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        searchBar.onClick { (activity as SchoolBaseActivity).goto(SearchActivity::class.java) }

        join.onClick {
            findNavController().navigate(
                R.id.action_joinOrganizationFragment_to_addSchoolFragment2
            )
        }
    }

    override fun initViews() {
        searchBar = binding?.JoinOrganizationSearchBar!!
        join = binding?.JoinOrganizationFragmentJoinBtn!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }

}