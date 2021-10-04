package ru.android.mvp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.android.mvp.databinding.FragmentUsersListBinding
import ru.android.mvp.models.GithubUsersRepo
import ru.android.mvp.presenters.UsersPresenter
import ru.android.mvp.ui.abs.AbsFragment
import ru.android.mvp.utils.ImageLoader
import ru.android.mvp.utils.schedulers.Schedulers
import ru.android.mvp.views.UsersView
import javax.inject.Inject

class UsersListFragment : AbsFragment(), UsersView {
    private val binding: FragmentUsersListBinding by viewBinding(CreateMethod.INFLATE)
    private var adapter: UserListAdapter? = null

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repo: GithubUsersRepo

    @Inject
    lateinit var schedulers: Schedulers
    private val userPresenter by moxyPresenter {
        UsersPresenter(
            repo,
            router,
            schedulers
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun init() = with(binding) {
        this.recycleViewUserList.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(userPresenter.userListPresenter, imageLoader)
        this.recycleViewUserList.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(e: Throwable) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }
}