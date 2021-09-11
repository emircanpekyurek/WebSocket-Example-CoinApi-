import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

fun Continuation<Unit>.resume() = this.resume(Unit)
