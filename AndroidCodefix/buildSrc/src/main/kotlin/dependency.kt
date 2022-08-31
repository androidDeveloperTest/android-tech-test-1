sealed class Dependency(private val version: String, private val pakage: String) {
    fun full() = "$pakage:$version"

    sealed class Kapt(private val version: String, private val pakage: String) :
        Dependency(version, pakage) {
        object Dagger : Kapt(version = Versions.dagger2, pakage = "com.google.dagger:dagger-compiler")
        object Hilt :
            Kapt(version = Versions.hilt, pakage = "com.google.dagger:hilt-android-compiler")

        object RoomCompiler :
            Kapt(version = Versions.room, pakage = "androidx.room:room-compiler")
    }

    sealed class AnnotationProcessor(private val version: String, private val pakage: String) :
        Dependency(version, pakage) {

    }

    object Coroutines :
        Dependency(
            version = Versions.coroutines,
            pakage = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
        )

    object DateTime : Dependency(version = Versions.dateTime, pakage = "org.jetbrains.kotlinx:kotlinx-datetime")

    object CoreKtx : Dependency(version = Versions.androidCore, pakage = "androidx.core:core-ktx")
    object LegacySupport : Dependency(version = Versions.legacySupport, pakage = "androidx.legacy:legacy-support-v4")
    object RecyclerView : Dependency(version = Versions.recyclerView, pakage = "androidx.recyclerview:recyclerview")
    object AppCompat :
        Dependency(version = Versions.appCompat, pakage = "androidx.appcompat:appcompat")

    object ViewModelKtx : Dependency(
        version = Versions.viewModelKtx,
        pakage = "androidx.lifecycle:lifecycle-viewmodel-ktx"
    )

    object FragmentKtx :
        Dependency(version = Versions.fragmentKtx, pakage = "androidx.fragment:fragment-ktx")

    object LifecycleRuntimeKtx : Dependency(
        version = Versions.lifecycleRuntimeKtx,
        pakage = "androidx.lifecycle:lifecycle-runtime-ktx"
    )

    object NavigationRuntime : Dependency(
        version = Versions.navigation,
        pakage = "androidx.navigation:navigation-runtime-ktx"
    )

    object NavigationFragment : Dependency(
        version = Versions.navigation,
        pakage = "androidx.navigation:navigation-fragment-ktx"
    )

    object Coil : Dependency(version = Versions.coil, pakage = "io.coil-kt:coil")

    object NavigationUi :
        Dependency(version = Versions.navigation, pakage = "androidx.navigation:navigation-ui-ktx")

    object Material :
        Dependency(version = Versions.material, pakage = "com.google.android.material:material")

    object ConstraintLayout :
        Dependency(
            version = Versions.constraintLayout,
            pakage = "androidx.constraintlayout:constraintlayout"
        )

    object Hilt : Dependency(version = Versions.hilt, "com.google.dagger:hilt-android")
    object Dagger : Dependency(version = Versions.dagger2, "com.google.dagger:dagger")

    object RoomRuntime : Dependency(version = Versions.room, pakage = "androidx.room:room-runtime")
    object RoomKtx : Dependency(version = Versions.room, pakage = "androidx.room:room-ktx")

    object Gson : Dependency(version = Versions.gson, pakage = "com.google.code.gson:gson")
    object Retrofit2 :
        Dependency(version = Versions.retrofit2, pakage = "com.squareup.retrofit2:retrofit")

    object Retrofit2GsonAdapter : Dependency(
        version = Versions.gson,
        pakage = "com.squareup.retrofit2:converter-gson"
    )
    object Retrofit2LoggingInterceptor : Dependency(version = Versions.loggingInterceptor, pakage = "com.squareup.okhttp3:logging-interceptor")



    object JUnit : Dependency(version = Versions.junit, pakage = "junit:junit")
    object MockK : Dependency(version = Versions.mockK, pakage = "io.mockk:mockk")
    object CoroutineTest : Dependency(version = Versions.coroutineTest, pakage = "org.jetbrains.kotlinx:kotlinx-coroutines-test")

    object AndroidTestJUnitExt :
        Dependency(version = Versions.androidTestJUnitExt, pakage = "androidx.test.ext:junit")
}