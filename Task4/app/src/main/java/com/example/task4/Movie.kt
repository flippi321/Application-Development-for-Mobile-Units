import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val description: String,
    val image: String // This can be a URL or a file path, based on your requirement
) : Parcelable
