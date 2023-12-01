package helpers

class ReadFile (private val fileName: String) {
    fun getString(): String =  javaClass.classLoader.getResource(fileName)!!.readText()

    fun getListOfStrings(): List<String> = getString().lines()
}