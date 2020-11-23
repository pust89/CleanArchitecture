package com.pustovit.tmdbclient.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.pustovit.tmdbclient.LOG_TAG
import com.pustovit.tmdbclient.R
import timber.log.Timber
import javax.inject.Inject


abstract class BaseFragment<BINDING : ViewDataBinding, VM : BaseFragmentViewModel, VMF : ViewModelProvider.Factory>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: VMF

    protected lateinit var binding: BINDING

    protected lateinit var viewModel: VM

    /**
     * Pass in this variable a link to your SwipeRefreshLayout, if you have it.
     * Or null.
     * For example:
     *  get() = binding.swipeContainer
     * It used in [showUploading]
     */
    abstract val swipeRefreshLayout: SwipeRefreshLayout?


    /**
     * Implement fragment injection here.
     *  For example:
     *      override fun injectFragment() {
    App.instance.presentationComponent.injectMovieFragment(this)
    }
     */
    abstract fun injectFragment()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        observeLoadingLiveData()
        observeErrorLiveData()
        return binding.root
    }

    private fun observeLoadingLiveData() {
        viewModel.loadingDialog.observe(viewLifecycleOwner, Observer {
            showUploading(it)
        })
    }

    private fun observeErrorLiveData() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                displayErrorSnackbar(it)
            }
        })
    }

    private fun displayErrorSnackbar(message: String) {

        val errorSnackbar = Snackbar
            .make(requireView(), "Something went wrong:\n$message \n", Snackbar.LENGTH_INDEFINITE)
            .setAction("OK") {
                viewModel.displayErrorDone()
            }
        val textView = errorSnackbar.view.findViewById<TextView>(R.id.snackbar_text)
        textView.maxLines = 40 // show multiple line
        errorSnackbar.show()

    }

    /**
     * Method shows/hides loading.
     */
    private fun showUploading(uploadingState: Boolean) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout!!.isRefreshing = uploadingState
    }


}