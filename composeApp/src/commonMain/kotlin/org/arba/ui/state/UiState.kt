package org.arba.ui.state

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable

sealed class UiState<out T> {
    data object Idle : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()

    @Composable
    fun DisplayResult(
        onIdle: (@Composable () -> Unit)? = null,
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (String) -> Unit,
    ) {
        AnimatedContent(
            targetState = this,
            label = "Content Animation"
        ) { state ->
            when (state) {
                is Idle -> {
                    onIdle?.invoke()
                }

                is Loading -> {
                    onLoading()
                }

                is Success -> {
                    onSuccess(state.data)
                }

                is Error -> {
                    onError(state.message)
                }

                else -> {}
            }
        }
    }
}