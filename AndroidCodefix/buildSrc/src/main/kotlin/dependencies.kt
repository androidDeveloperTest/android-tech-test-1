object Dependencies {
    private val common = listOf(
        Dependency.Coroutines,
        Dependency.DateTime,
    )

    private val android = listOf(
        Dependency.CoreKtx,
    )

    private val androidTest = listOf(
        Dependency.AndroidTestJUnitExt,
    )

    private val commonTest = listOf(
        Dependency.JUnit,
        Dependency.MockK,
        Dependency.CoroutineTest,
    )

    val app = listOf(
        Dependency.Hilt,
        Dependency.Kapt.Hilt,
    )

    val domain = listOf(
        Dependency.Dagger,
        Dependency.Kapt.Dagger,
    ).plus(common).plus(commonTest)

    val presentation = listOf(
        Dependency.AppCompat,
        Dependency.ViewModelKtx,
        Dependency.LegacySupport,
        Dependency.RecyclerView,
        Dependency.FragmentKtx,
        Dependency.NavigationRuntime,
        Dependency.NavigationFragment,
        Dependency.NavigationUi,
        Dependency.LifecycleRuntimeKtx,
        Dependency.Coil,
        Dependency.Material,
        Dependency.ConstraintLayout,
        Dependency.Hilt,
        Dependency.Kapt.Hilt,
    ).plus(common).plus(commonTest).plus(android).plus(androidTest)

    val data = listOf(
        Dependency.RoomRuntime,
        Dependency.RoomKtx,
        Dependency.Kapt.RoomCompiler,
        Dependency.Gson,
        Dependency.Retrofit2,
        Dependency.Retrofit2GsonAdapter,
        Dependency.Retrofit2LoggingInterceptor,
        Dependency.Hilt,
        Dependency.Kapt.Hilt,
    ).plus(common).plus(commonTest).plus(android).plus(androidTest)
}