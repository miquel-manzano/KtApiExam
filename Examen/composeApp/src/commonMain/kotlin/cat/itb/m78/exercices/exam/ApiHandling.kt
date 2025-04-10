package cat.itb.m78.exercices.exam

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json
@Serializable
data class Student(
    @SerialName("id") val studentId: Int,
    @SerialName("name") val studentName: String,
    @SerialName("surnames") val studentSurnames: String,
    @SerialName("email") val studentEmail: String,
    @SerialName("photo_link") val studentPhoto: String,
)

object ApiStudents {
    private const val URL = "https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json"
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(URL).body<List<Student>>()
    //suspend fun selectGame(id: Int) = client.get(URLFind + id).body<Game>()
}