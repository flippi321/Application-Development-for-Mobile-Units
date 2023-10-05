import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val description: String,
    val image: String // Location of image
) : Parcelable
