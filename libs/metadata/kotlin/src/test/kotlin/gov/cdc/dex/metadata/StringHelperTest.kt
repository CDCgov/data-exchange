package gov.cdc.dex.metadata

import gov.cdc.dex.metadata.StringHelper.normalize
import org.junit.jupiter.api.Test

class StringHelperTest {

    @Test
    fun normalize() {
        assert("field_value" == "Field Value".normalize())
        assert("field_value" == "FIELD__VALUE".normalize())
        assert("field_value" == "FIELD $%VALUE%".normalize())
        assert("field_value" == "   FIELD   VALUE   ".normalize())
        assert("field_value" == "field_value".normalize())
        assert("field_value" == "field.value".normalize())
        assert("field_value" == "field/value".normalize())
        assert("field_value" == "field-value".normalize())

        assert("field_one_and_two" == "Field ONE & TWO".normalize())

    }
}