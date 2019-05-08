@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.nikitamaslov.storage.database

import com.nikitamaslov.storage.model.common.Common
import com.nikitamaslov.storage.model.single.Single
import io.realm.*
import io.realm.kotlin.addChangeListener
import io.realm.kotlin.delete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.map
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.reflect.KClass

internal typealias RealmProvider = () -> Realm

internal class Database(private val realmProvider: RealmProvider) {

    @JvmName("receiveCommon")
    suspend fun <T : Common> receive(kClass: KClass<T>): ReceiveChannel<List<T>> = read { realm ->
        realm.where(kClass.java)
            .openSubscriptionToAll()
    }

    @JvmName("receiveSingle")
    suspend fun <T : Single> receive(kClass: KClass<T>): ReceiveChannel<T?> = read { realm ->
        realm.where(kClass.java)
            .openSubscriptionToFirstOrNull()
    }

    suspend fun <T : Common> insert(vararg objects: T) = write { realm ->
        realm.insert(objects.toList())
    }

    suspend fun <T : Single> insertOrReplace(obj: T) = write { realm ->
        realm.insertOrUpdate(obj)
    }

    suspend inline fun <reified T : Common> deleteAll() = write { realm ->
        realm.delete<T>()
    }

    suspend inline fun <reified T : Single> delete() = write { realm ->
        realm.delete<T>()
    }


    private suspend fun <T : RealmModel> RealmQuery<T>.awaitAll(): List<T> =
        suspendCancellableCoroutine { continuation ->
            val all = findAllAsync()
            val listener = RealmChangeListener<RealmResults<T>>(continuation::resume)
            all.addChangeListener(listener)
        }

    private suspend fun <T : RealmModel> RealmQuery<T>.awaitFirstOrNull(): T? =
        suspendCancellableCoroutine { continuation ->
            val firstOrNull = findFirstAsync()
            val listener = RealmChangeListener<T>(continuation::resume)
            firstOrNull.addChangeListener(listener)
        }

    private suspend fun <T : RealmModel> RealmQuery<T>.openSubscriptionToFirstOrNull(
    ): ReceiveChannel<T?> = coroutineScope {
        produce<T?> {
            val firstOrNull = findFirstAsync()
            val listener = RealmChangeListener<T> { launch { send(it) } }
            firstOrNull.addChangeListener(listener)
        }
    }

    private suspend fun <T : RealmModel> RealmQuery<T>.openSubscriptionToAll(
    ): ReceiveChannel<List<T>> = coroutineScope {
        produce<List<T>> {
            val all = findAllAsync()
            val listener = RealmChangeListener<RealmResults<T>> { launch { send(it) } }
            all.addChangeListener(listener)
        }
    }


    private suspend fun <T> read(block: suspend CoroutineScope.(Realm) -> T) =
        realmProvider().read(block)

    private suspend fun write(block: (Realm) -> Unit) = realmProvider().write(block)


    private suspend fun <T> Realm.read(block: suspend CoroutineScope.(Realm) -> T): T =
        coroutineScope {
            use { realm -> block(realm) }
        }

    private suspend fun Realm.write(block: (Realm) -> Unit) =
        suspendCancellableCoroutine<Unit> { continuation ->
            use { realm ->
                val transaction = Realm.Transaction(block)
                val onSuccess = Realm.Transaction.OnSuccess { continuation.resume(Unit) }
                val onError = Realm.Transaction.OnError { continuation.resumeWithException(it) }

                realm.executeTransactionAsync(transaction, onSuccess, onError)
            }
        }
}

internal suspend fun <T, U : Single> Database.insertOrReplace(
    obj: T,
    mapper: (T) -> U
) {
    val mapped = mapper(obj)
    insertOrReplace(mapped)
}

@JvmName("receiveSingle")
internal suspend inline fun <T, reified U : Single> Database.receive(
    crossinline mapper: (U) -> T
): ReceiveChannel<T?> = receive(U::class).map { it?.let(mapper) }

@JvmName("receiveCommon")
internal suspend inline fun <T, reified U : Common> Database.receive(
    crossinline mapper: (U) -> T
): ReceiveChannel<List<T>> = receive(U::class).map { it.map(mapper) }