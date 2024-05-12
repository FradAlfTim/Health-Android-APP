package com.example.assignmentproject.record

sealed interface IRecord {
    fun title(): String

    data object Reminder : IRecord {
        override fun title(): String {
            return "Reminder"
        }
    }

    data object Running : IRecord {
        override fun title(): String {
            return "Running"
        }
    }

    data object Sleep : IRecord {
        override fun title(): String {
            return "Sleep"
        }
    }

    data object Swimming : IRecord {
        override fun title(): String {
            return "Swimming"
        }
    }

    data object Walking : IRecord {
        override fun title(): String {
            return "Walking"
        }
    }
}

val navigationItems = listOf(
    IRecord.Sleep,
    IRecord.Walking,
    IRecord.Running,
    IRecord.Swimming,
    IRecord.Reminder,
)

fun String.coverToIRecord(): IRecord {
   return when (this) {
        "Reminder" -> {
            IRecord.Reminder
        }

        "Running" -> {
            IRecord.Running
        }

        "Sleep" -> {
            IRecord.Sleep
        }

        "Swimming" -> {
            IRecord.Swimming
        }

        "Walking" -> {
            IRecord.Walking
        }

        else -> {
            IRecord.Running
        }
    }
}
