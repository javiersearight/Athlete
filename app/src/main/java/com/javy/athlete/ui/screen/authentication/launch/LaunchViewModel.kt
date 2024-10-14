package com.javy.athlete.ui.screen.authentication.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javy.athlete.data.repository.AthleteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val repository: AthleteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LaunchUIState())
    val uiState = _uiState.asStateFlow()

    fun fetchAthlete() {
        viewModelScope.launch {
            runCatching {
                repository.athlete()
            }.onFailure { exception ->
                if (exception is HttpException) {
                    when (exception.code()) {
                        401 -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    isAuthenticated = false
                                )
                            }
                        }
                    }
                }
            }.onSuccess { athlete ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isAuthenticated = athlete != null
                    )
                }
            }
        }
    }
}

data class LaunchUIState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean? = null
)