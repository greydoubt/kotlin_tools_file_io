@Throws(IllegalArgumentException::class)
fun saferOpenFile(path: String, expectedDir: String?): File {
    val f = File(path)
    val canonicalPath = f.canonicalPath
    require(canonicalPath.startsWith(expectedDir!!))
    return f
}
