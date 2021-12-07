package moe.quill.nadare.attributes.attributes

annotation class Attribute(
    val name: String,
    val minLevel: Int = 1,
    val maxLevel: Int = 5,
    val tags: Array<String> = []
)
