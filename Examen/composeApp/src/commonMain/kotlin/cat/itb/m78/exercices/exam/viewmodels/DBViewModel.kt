package cat.itb.m78.exercices.exam.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.Query
import cat.itb.m78.exercices.db.AbsentStudentsDB
import cat.itb.m78.exercices.db.StudentsDB
import cat.itb.m78.exercices.exam.ApiStudents
import cat.itb.m78.exercices.exam.Student
import cat.itb.m78.exercices.p2.db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbsentStudent(
    val studentId: Int,
    val studentDate: String
)


class DBViewModel : ViewModel() {
    val absentStudentQueries = database.absentStudentsDBQueries
    var absentStudentsList = mutableStateOf(listOf<AbsentStudentsDB>())
    var absentNumStudent = mutableStateOf(listOf<Long>())

    fun addAbsentStudent(student: Student){
        absentStudentQueries.insertStudent(student.studentId.toLong())
    }

    fun reloadAbsentList(){
        absentStudentsList.value = absentStudentQueries.selectAll().executeAsList()
    }

    fun getNumAbsStudent(studentId: Int){
        absentNumStudent.value = absentStudentQueries.getNumStudentById(studentId.toLong()).executeAsList()
    }

    fun deleteAbs(studentId: AbsentStudentsDB){
        absentStudentQueries.deleteAbs(studentId.SID)
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            absentStudentsList.value = absentStudentQueries.selectAll().executeAsList()
        }
    }
}