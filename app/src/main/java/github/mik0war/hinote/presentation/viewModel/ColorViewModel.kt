package github.mik0war.hinote.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface ColorViewModel {

    fun saveColor(buttonsColor: Int, mainColor: Int): Job
    fun setColor(): Job
    class Base : ViewModel(), ColorViewModel {
        override fun saveColor(buttonsColor: Int, mainColor: Int) = viewModelScope.launch{

        }

        override fun setColor() = viewModelScope.launch{

        }
    }
}