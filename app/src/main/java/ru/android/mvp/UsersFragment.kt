package ru.android.mvp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.android.mvp.navigation.App.Navigation.router
import ru.android.mvp.databinding.FragmentUsersListBinding
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.navigation.AndroidScreens
import ru.android.mvp.navigation.BackButtonListener
import ru.android.mvp.presenters.UsersPresenter
import ru.android.mvp.views.UsersView

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = Fragment()
    }

    private val presenter: UsersPresenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), router, AndroidScreens()) }
    private var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersListBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun backButtonPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}