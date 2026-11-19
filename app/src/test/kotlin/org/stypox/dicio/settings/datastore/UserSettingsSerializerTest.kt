package org.stypox.dicio.settings.datastore

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserSettingsSerializerTest : StringSpec({
    "fresh installs default to Spanish" {
        UserSettingsSerializer.defaultValue.language shouldBe Language.LANGUAGE_ES
    }

    "fresh installs enable automatic speech input popup completion" {
        UserSettingsSerializer.defaultValue.autoFinishSttPopup shouldBe true
    }
})
