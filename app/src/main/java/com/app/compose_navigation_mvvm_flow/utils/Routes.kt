package com.app.compose_navigation_mvvm_flow.utils

object Routes {
    const val LIST_SCREEN="listScreen"
    const val DETAIL_SCREEN = "detailScreen/{${Values.IDVALUE}}"

    fun getSecondScreenPath(idValue: Int?): String =
        // to avoid null and empty strings
        if (idValue != null) "detailScreen/$idValue" else "detailScreen/Empty"

    object Values {
        const val IDVALUE = "idValue"
    }
}