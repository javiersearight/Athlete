package com.javy.athlete.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javy.athlete.data.repository.AthleteRepository
import com.javy.athlete.ui.model.Athlete
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AthleteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(isLoading = true)
        }

        fetchAthleteAndStats()
    }

    private fun fetchAthleteAndStats() {
        viewModelScope.launch {
            runCatching {
                repository.athleteAndStats()
            }.onFailure {
                // TODO implement universal error handling and error state
            }.onSuccess { flow ->
                flow.collect { athlete ->
                    _uiState.update {
                        it.copy(
                            athlete = athlete,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

data class HomeUIState(
    val athlete: Athlete? = null,
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean? = null
)