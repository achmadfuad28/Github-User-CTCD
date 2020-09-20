package comtest.ct.cd.achmadfuad.presentation.framework

import android.content.Context
import android.widget.Toast
import comtest.ct.cd.achmadfuad.domain.entities.ResultState
import retrofit2.HttpException


fun getResultStateError(error: Throwable): ResultState {
    return when (error) {
        is HttpException     -> {
            return when (error.code()) {
                400, 401, 403, 409, 406  -> {
                    ResultState.BadRequest(throwable = Throwable(getMessageFromBody(error)))
                }
                404  -> {
                    ResultState.NotFound(throwable = Throwable("Not Found"))
                }
                else -> {
                    ResultState.Error(throwable = error)
                }
            }

        }
        else                 -> {
            return ResultState.Error(throwable = Throwable(error.message))
        }
    }

}

private fun getMessageFromBody(throwable: HttpException): String {
    return throwable.message()
}

fun showToastErrorMessage(state: ResultState, context: Context) {
    when (state) {
        is ResultState.Conflict     -> {
            Toast.makeText(context, state.throwable.message, Toast.LENGTH_LONG).show()

        }
        is ResultState.BadRequest   -> {
            Toast.makeText(context, state.throwable.message, Toast.LENGTH_LONG).show()

        }
        is ResultState.NoConnection -> {
            Toast.makeText(context, state.throwable.message, Toast.LENGTH_LONG).show()

        }
        is ResultState.NotFound     -> {
            Toast.makeText(context, state.throwable.message, Toast.LENGTH_LONG).show()

        }
        is ResultState.Forbidden    -> {
            Toast.makeText(context, state.throwable.message, Toast.LENGTH_LONG).show()

        }
        is ResultState.Unauthorized -> {
            Toast.makeText(context, state.throwable.message, Toast.LENGTH_LONG).show()

        }
    }

}