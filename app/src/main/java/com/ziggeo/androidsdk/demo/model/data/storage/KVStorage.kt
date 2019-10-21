package com.ziggeo.androidsdk.demo.model.data.storage


/**
 * Created by Alexander Bedulin on 21-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */
interface KVStorage {
    fun put(key: Int, value: Any?)
    fun get(key: Int): Any?
}