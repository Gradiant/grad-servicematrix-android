//ANDROID PORT
package id.walt.servicematrix.utils

object AndroidUtils {
    private lateinit var androidDataDir: String
    private lateinit var dataRoot: String

    fun setAndroidDataDir(androidDataDir: String) {
        this.androidDataDir = androidDataDir
    }

    fun getAndroidDataDir(): String {
       return androidDataDir
    }

    fun setDataRoot(dataRoot: String) {
       this.dataRoot = dataRoot
    }

    fun getDataRoot(): String {
        return dataRoot
    }
}
//ANDROID PORT

