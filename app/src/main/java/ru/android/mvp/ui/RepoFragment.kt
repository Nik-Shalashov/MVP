package ru.android.mvp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.android.mvp.databinding.FragmentRepoBinding
import ru.android.mvp.models.retrofit.GithubRepos
import ru.android.mvp.models.RepositoryFactory
import ru.android.mvp.models.network.NetworkStatusFactory
import ru.android.mvp.presenters.RepoPresenter
import ru.android.mvp.utils.schedulers.SchedulersFactory
import ru.android.mvp.views.RepoView

class RepoFragment : MvpAppCompatFragment(), RepoView {
    companion object {
        private const val ARG_REPO_URL = "repo"
        fun newInstance(repoUrl: String?) =
            RepoFragment().apply {
                arguments = bundleOf(ARG_REPO_URL to repoUrl)
            }
    }
    private val repoUrl: String? by lazy {
        arguments?.getString(ARG_REPO_URL)
    }
    private val binding : FragmentRepoBinding by viewBinding(CreateMethod.INFLATE)
    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            RepositoryFactory.create(NetworkStatusFactory.create(context)),
            repoUrl,
            SchedulersFactory.create()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoName(repo: GithubRepos) {
        binding.forks.text = "Repo forks is ${repo.forks}"
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoForks(repo: GithubRepos) {
        binding.forks.text = "Repo Name is ${repo.name}"
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoDate(repo: GithubRepos) {
        binding.date.text = "Creted at ${repo.date}"
    }

    @SuppressLint("SetTextI18n")
    override fun showRepoLanguage(repo: GithubRepos) {
        binding.language.text = "Using languages ${repo.language}"
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }
}