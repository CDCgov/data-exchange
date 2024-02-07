package gov.cdc.dex.metadata


public object StringHelper {
 /**
    * All metadata attributes should follow snake_case syntax.
    *
    * This method helps normalize the names and remove special characters
    */ 

    fun String.normalize(str: String): String {
        val replaceableChars = mapOf(" " to "_",
                                     "-" to "_",
                                     "/" to "_",
                                     "." to "_",
                                     "&" to "_and_")

        var rr1 = str.trim().lowercase()
        replaceableChars.forEach {
            rr1 = rr1.replace(it.key, it.value)
        }
        //remove duplicate underscores based on replacements above and remove all other unknown chars
        return rr1.replace("(_)\\1+".toRegex(), "_" ).replace("[^A-Z a-z 0-9 _\\.]".toRegex(), "")
    }
}