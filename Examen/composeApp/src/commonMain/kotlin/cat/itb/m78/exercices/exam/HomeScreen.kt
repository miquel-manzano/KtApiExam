package cat.itb.m78.exercices.exam

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.exam.screens.AbsenceStudentsScreen
import cat.itb.m78.exercices.exam.screens.NumAbStudentsListScreen
import cat.itb.m78.exercices.exam.screens.StudentsListScreen
import cat.itb.m78.exercices.exam.viewmodels.ApiViewModel
import cat.itb.m78.exercices.exam.viewmodels.DBViewModel
import kotlinx.serialization.Serializable


object Screens {
    @Serializable
    data object StudentsList
    @Serializable
    data object NumAbStudentsList
    @Serializable
    data object AbsenceStudents
}


@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    val modelApi = viewModel { ApiViewModel() }
    val modelBD = viewModel { DBViewModel() }

    Scaffold(bottomBar = {
        NavigationBar{
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate(Screens.StudentsList)},
                icon = {Icon(imageVector = Icons.Default.Home, contentDescription = null)},
                label = {Text("Home")}
            )
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate(Screens.NumAbStudentsList)},
                icon = {Icon(imageVector = Icons.Default.Call, contentDescription = null)},
                label = {Text("Home 2")}
            )
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate((Screens.AbsenceStudents))},
                icon = {Icon(imageVector = Icons.Default.Add, contentDescription = null)},
                label = {Text("Absence Students")}
            )
        }
    })
    {
        NavHost(navController = navController, startDestination = Screens.StudentsList) {
            composable<Screens.StudentsList>{
                StudentsListScreen(
                    modelApi.studentsList.value
                )
            }
            composable<Screens.NumAbStudentsList>{
                NumAbStudentsListScreen(
                    modelApi.studentsList.value
                )
            }
            composable<Screens.AbsenceStudents> {
                AbsenceStudentsScreen(
                    modelBD.absentStudentsList.value
                )
            }
        }
    }
}