package com.shourov.apps.pacedream.core.data

data class AccountSetupScreenData(
    val processIndex: Int ,
    val totalProcessCount: Int,
    val accountCreationProcess: AccountCreationProcess,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
)
