package com.github.triplet.gradle.common.validation

import org.gradle.util.GradleVersion
import org.gradle.util.VersionNumber

internal class RuntimeValidator(
        private val currentGradleVersion: GradleVersion,
        private val minGradleVersion: GradleVersion,

        private val currentAgpVersion: VersionNumber,
        private val minAgpVersion: VersionNumber
) {
    fun validate() {
        validateGradle()
        validateAgp()
    }

    private fun validateGradle() {
        check(currentGradleVersion >= minGradleVersion) {
            """
            |Gradle Play Publisher's minimum Gradle version is at least $minGradleVersion and yours
            |is $currentGradleVersion. Find the latest version at
            |https://github.com/gradle/gradle/releases/latest, then run
            |$ ./gradlew wrapper --gradle-version=${"$"}LATEST --distribution-type=ALL
            """.trimMargin()
        }
    }

    private fun validateAgp() {
        check(currentAgpVersion >= minAgpVersion) {
            """
            |Gradle Play Publisher's minimum Android Gradle Plugin version is at least
            |$minAgpVersion and yours is $currentAgpVersion. Find the latest version and upgrade
            |instructions at https://developer.android.com/studio/releases/gradle-plugin.
            """.trimMargin()
        }
    }
}
