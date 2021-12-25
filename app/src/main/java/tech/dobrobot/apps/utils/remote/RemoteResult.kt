package tech.dobrobot.apps.utils.remote

sealed class RemoteResult<out R> {
    data class Success<out T>(val data: T): RemoteResult<T>()
    data class Error(val message: String): RemoteResult<Nothing>()
    object Loading: RemoteResult<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success [data = $data]"
            is Error -> "Error [message = $message]"
            is Loading -> "Loading"
        }
    }
}

val RemoteResult<*>.succeeded
    get() = this is RemoteResult.Success && data != null