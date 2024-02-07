package gov.cdc.dex.metadata

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.*

public object DateHelper {

    private const val ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    /**
    * All metadata Date attributes should be inputed as ISO 8601 Dates.
    *
    * This method helps translate Dates into ISO-8601 strings. 
    */
    @JvmName("toIsoStringNullable")
    fun Date?.asISO8601(): String? = this?.asISO8601()
    fun Date.asISO8601(): String  = SimpleDateFormat(ISO8601).format(this)
    /* Same functions but for OffsetDateTime */
    @JvmName("toIsoStringNullableOffsetDateTime")
    fun OffsetDateTime?.asISO8601(): String? = this?.asISO8601()
    fun OffsetDateTime.asISO8601(): String = Date.from(this.toInstant()).asISO8601()
}