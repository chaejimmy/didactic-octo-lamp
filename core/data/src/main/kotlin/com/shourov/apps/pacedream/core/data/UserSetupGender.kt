
package com.shourov.apps.pacedream.core.data

import androidx.annotation.StringRes

enum class UserSetupGender(
    @StringRes val value: Int,
) {
    MALE(R.string.core_data_male),
    FEMALE(R.string.core_data_female),
    PREFERS_NOT_TO_SAY(R.string.core_data_prefers_not_to_say),
}