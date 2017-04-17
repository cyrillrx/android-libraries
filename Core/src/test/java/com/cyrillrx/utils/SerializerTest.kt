package com.cyrillrx.utils

import org.junit.Assert
import org.junit.Test


/**
 * @author Cyril Leroux
 * *         Created 04/04/2017.
 */
class SerializerTest {

    data class DataObject(val key1: String, val key2: String)

    @Test
    fun serialize() {

        val data = DataObject("toto", "<tag />")

        val actual = Serializer.serialize(data)
        val expected = """{"key1":"toto","key2":"\u003ctag /\u003e"}"""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deserialize() {

        val serialized = """{"key1":"toto","key2":"\u003ctag /\u003e"}"""
        val data: DataObject = Serializer.deserialize(serialized, DataObject::class.java)

        Assert.assertEquals("toto", data.key1)
        Assert.assertEquals("<tag />", data.key2)

        val serialized2 = """{"key1":"toto","key2":"<tag />"}"""
        val data2: DataObject = Serializer.deserialize(
                serialized2,
                DataObject::class.java)

        Assert.assertEquals("toto", data2.key1)
        Assert.assertEquals("<tag />", data2.key2)
    }

    @Test
    fun serializeNoEscaping() {

        val data = DataObject("toto", "<tag />")

        val actual = Serializer.serializeNoEscaping(data)
        val expected = """{"key1":"toto","key2":"<tag />"}"""

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deserializeNoEscaping() {

        val serialized = """{"key1":"toto","key2":"\u003ctag /\u003e"}"""

        val data: DataObject = Serializer.deserializeNoEscaping(serialized, DataObject::class.java)

        Assert.assertEquals("toto", data.key1)
        Assert.assertEquals("<tag />", data.key2)
    }

    @Test
    fun prettyPrint() {

        val data = DataObject("toto", "<tag />")

        val actual = Serializer.prettyPrint(data)
        val expected =
                """{
  "key1": "toto",
  "key2": "<tag />"
}"""

        Assert.assertEquals(expected, actual)
    }

}