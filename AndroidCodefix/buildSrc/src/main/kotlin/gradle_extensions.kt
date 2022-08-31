import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.customImplementation(dependencies: List<Dependency>) {
    dependencies.forEach {
        configuration(
            when (it) {
                is Dependency.Kapt -> {
                    println("kapt: ${it.full()}")
                    "kapt"
                }
                is Dependency.AnnotationProcessor -> {
                    println("annotationProcessor: ${it.full()}")
                    "annotationProcessor"
                }
                else -> {
                    println("implementation: ${it.full()}")
                    "implementation"
                }
            },
            it
        )
    }
}

private fun DependencyHandler.configuration(name: String, dependency: Dependency) {
    add(name, dependency.full())
}

private fun DependencyHandler.project(projectName: String) {
    add("implementation", project(projectName, "default"))
}
