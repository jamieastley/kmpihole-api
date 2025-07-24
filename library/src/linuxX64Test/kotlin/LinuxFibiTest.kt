package dev.jamieastley.kmpihole.api

import kotlin.test.Test
import kotlin.test.assertEquals

class LinuxFibiTest {

    @Test
    fun `test 3rd element`() {
        assertEquals(8, generateFibi().take(3).last())
    }
}
