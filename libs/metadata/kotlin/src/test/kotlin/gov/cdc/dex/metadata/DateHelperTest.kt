package gov.cdc.dex.metadata

import gov.cdc.dex.metadata.DateHelper.asISO8601
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


class DateHelperTest {

    @Test
    fun testDates() {
        val defaultZoneId = ZoneId.systemDefault()
        val localDate = LocalDate.of(2018, 12, 25)

        val xmas = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant())
        val xmasISO = xmas.asISO8601()
        println(xmasISO)
        assert(xmasISO.startsWith("2018-12-25"))
    }
}