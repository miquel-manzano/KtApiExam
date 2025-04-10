package cat.itb.m78.exercices.exam.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.exam.ApiStudents
import cat.itb.m78.exercices.exam.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiViewModel : ViewModel() {
    var studentsList = mutableStateOf(listOf<Student>())

    init {
        viewModelScope.launch(Dispatchers.Default) {
            studentsList.value = ApiStudents.list()
        }
    }
}