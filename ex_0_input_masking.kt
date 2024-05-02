protected fun sanitizeFilename(displayName: String): String {
    val badCharacters = arrayOf("..", "/")
    val segments = displayName.split("/")
    var fileName = segments[segments.size - 1]
    for (suspString in badCharacters) {
        fileName = fileName.replace(suspString, "_")
    }
    return fileName
}

val displayName = returnCursor.getString(nameIndex)
val fileName = sanitizeFilename(displayName)
val filePath = File(context.filesDir, fileName).path

// saferOpenFile defined in Android developer documentation
val outputFile = saferOpenFile(filePath, context.filesDir.canonicalPath)

// fd obtained using Requesting a shared file from Android developer
// documentation

val inputStream = FileInputStream(fd)

// Copy the contents of the file to the new file
try {
    val outputStream = FileOutputStream(outputFile)
    val buffer = ByteArray(1024)
    var length: Int
    while (inputStream.read(buffer).also { length = it } > 0) {
        outputStream.write(buffer, 0, length)
    }
} catch (e: IOException) {
    // Handle exception
}

