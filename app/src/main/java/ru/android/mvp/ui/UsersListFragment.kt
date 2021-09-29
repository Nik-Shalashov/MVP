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
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.android.mvp.App.Navigation.router
import ru.android.mvp.databinding.FragmentUsersListBinding
import ru.android.mvp.models.RepositoryFactory
import ru.android.mvp.models.network.NetworkStatusFactory
import ru.android.mvp.presenters.UsersPresenter
import ru.android.mvp.utils.ImageLoader
import ru.android.mvp.utils.schedulers.SchedulersFactory
import ru.android.mvp.views.UsersView


class UsersListFragment : MvpAppCompatFragment(), UsersView {
    private val binding: FragmentUsersListBinding by viewBinding(CreateMethod.INFLATE)
    private val imageLoader = ImageLoader
    private var adapter: UserListAdapter? = null
    private val userPresenter by moxyPresenter {
        UsersPresenter(
            RepositoryFactory.create(NetworkStatusFactory.create(context)),
            router,
            SchedulersFactory.create()
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