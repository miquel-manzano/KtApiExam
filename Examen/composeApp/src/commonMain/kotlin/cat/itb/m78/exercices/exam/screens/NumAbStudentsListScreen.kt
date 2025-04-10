package cat.itb.m78.exercices.exam.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.exam.Student
import cat.itb.m78.exercices.exam.viewmodels.DBViewModel
import coil3.compose.AsyncImage

@Composable
fun NumAbStudentsListScreen(students: List<Student>){
    val model = viewModel { DBViewModel() }
    model.reloadAbsentList()

    if(students.any()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            LazyColumn(
                modifier = Modifier
                    .padding(15.dp)
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)){
                items(students){ student->
                    model.getNumAbsStudent(student.studentId)
                    Card(modifier = Modifier.width(500.dp).height(300.dp)){
                        Column(){
                            Text(student.studentName)
                            Text(student.studentSurnames)
                            Text(student.studentEmail)
                            AsyncImage(
                                model = student.studentPhoto,
                                contentDescription = student.studentId.toString()
                            )
                            Text("Num faltes: " + model.absentNumStudent.value)
                        }
                    }
                }
            }
        }
    }
    else{
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}