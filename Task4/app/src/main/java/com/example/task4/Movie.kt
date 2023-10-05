import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val description: String,
    val image: String // Name of image this movie, found in res/drawable
) : Parcelable
