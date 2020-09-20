package comtest.ct.cd.achmadfuad.presentation.widget

interface UrlSourceImageView {

    val loadingPlaceHolder: Int
    val errorPlaceHolder: Int

    fun setImageUrl(url: String)
}
