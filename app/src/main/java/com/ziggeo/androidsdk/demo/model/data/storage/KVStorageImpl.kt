package com.ziggeo.androidsdk.demo.model.data.storage

import android.util.SparseArray


/**
 * Created by Alexander Bedulin on 21-Oct-19.
 * Ziggeo, Inc.
 * alexb@ziggeo.com
 */

class KVStorageImpl : KVStorage {
    private val arr = SparseArray<Any>()

    override fun put(key: Int, value: Any?) = arr.put(key, value)
    override fun get(key: Int): Any? = arr.get(key)

}