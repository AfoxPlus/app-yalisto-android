package com.afoxplus.yalisto.repositories

import android.app.Activity
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalPreference @Inject constructor(@ApplicationContext context: Context) {

    private val context: WeakReference<Context> = WeakReference(context)

    /**
     * @param objectToWrite
     * @param filename
     */
    fun <T> writeObjectToFile(objectToWrite: T, filename: String?) {
        var objectOut: ObjectOutputStream? = null
        try {
            context.get()?.let { ctx ->
                val fileOut = ctx.openFileOutput(filename, Activity.MODE_PRIVATE)
                objectOut = ObjectOutputStream(fileOut)
                objectOut?.writeObject(objectToWrite)
                fileOut.fd.sync()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                objectOut?.close()
            } catch (e: IOException) {
                // do nowt
            }
        }
    }

    /**
     * @param filename
     * @return
     */
    fun <T> readObjectFromFile(filename: String?): T? {
        var objectIn: ObjectInputStream? = null
        var objectToRead: T? = null
        try {
            context.get()?.let { ctx ->
                val fileIn = ctx.openFileInput(filename)
                objectIn = ObjectInputStream(fileIn)
                objectToRead = objectIn?.readObject() as T
            }
        } catch (e: FileNotFoundException) {
            // Do nothing
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } finally {
            try {
                objectIn?.close()
            } catch (e: IOException) {
                // do nowt
            }
        }
        return objectToRead
    }
}