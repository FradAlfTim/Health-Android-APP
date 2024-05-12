package com.example.assignmentproject.record


data class Distance(val distance: Float, val unit: String) {
    override fun toString(): String {
        return "$distance $unit"
    }
}

val DateOptions = listOf("2023-04-28", "2023-04-29", "2023-04-30", "2023-05-01")
val DurationOptions = listOf(0.5f, 1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f, 5.0f)
val DistanceOptions = listOf(1.0f, 2.0f, 3.0f, 5.0f, 10.0f, 15.0f, 20.0f, 25.0f, 30.0f, 35.0f)


