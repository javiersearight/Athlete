package com.javy.athlete.ui.screen.authentication.authenticate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javy.athlete.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUIState())
    val uiState = _uiState.asStateFlow()

    fun fetchToken(code: String, navigateToHomeScreen: () -> Unit) {
        viewModelScope.launch {
            runCatching {
                repository.token(code)
            }.onFailure {
                // TODO implement universal error handling and error state
            }.onSuccess { isAuthenticated ->
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
                if (isAuthenticated) navigateToHomeScreen()
            }
        }
    }
}

data class AuthenticationUIState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean? = null
)