package com.az.reciepeapp.presentation.components.utils

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnackbarController(
    private val scope: CoroutineScope
) {

    private var snackbarJob: Job? = null

    init {

        cancelActiveJob()
    }

    fun getScope() = scope

    fun showSnackbar(scaffoldState: ScaffoldState, mess: String, actionLabel: String) {

        if (snackbarJob == null) {
            snackbarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    mess,
                    actionLabel
                )

                cancelActiveJob()
            }

        } else {
            cancelActiveJob()
            snackbarJob = scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    mess,
                    actionLabel
                )
                cancelActiveJob()
            }
        }
    }

    private fun cancelActiveJob() {
        snackbarJob?.let { job ->
            job.cancel()
            snackbarJob = Job()
        }
    }
}