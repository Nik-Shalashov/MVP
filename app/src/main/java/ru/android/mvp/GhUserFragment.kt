package ru.android.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.android.mvp.databinding.FragmentGhUserBinding
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.presenters.GhUserPresenter
import ru.android.mvp.views.GhUserView

class GhUserFragment(private val pos: Int) : MvpAppCompatFragment(), GhUserView {

    private var vb: FragmentGhUserBinding? = null
    private val presenter: GhUserPresenter by moxyPresenter { GhUserPresenter(GithubUsersRepo()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentGhUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLogin()
    }

    override fun setLogin() {
        vb?.ghUserLogin?.text = presenter.setLogin(pos).toString()
    }

    companion object {
        fun newInstance(pos: Int) = Fragment(pos)
    }
}