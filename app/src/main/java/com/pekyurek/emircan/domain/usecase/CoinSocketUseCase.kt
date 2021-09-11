package com.pekyurek.emircan.domain.usecase

import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.exception.ServiceException
import com.pekyurek.emircan.domain.model.coin_socket.CoinSocketRequest
import com.pekyurek.emircan.domain.model.coin_socket.CoinSocketResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI
import javax.inject.Inject

class CoinSocketUseCase @Inject constructor(private val moshi: Moshi, private val url: String) :
    UseCase<CoinSocketRequest, CoinSocketResponse> {

    val flow = MutableSharedFlow<ResultStatus<CoinSocketResponse>>()

    private val coinSocketRequestJsonAdapter: JsonAdapter<CoinSocketRequest> by lazy {
        moshi.adapter(CoinSocketRequest::class.java)
    }
    private val coinSocketResponseJsonAdapter: JsonAdapter<CoinSocketResponse> by lazy {
        moshi.adapter(CoinSocketResponse::class.java)
    }

    private lateinit var webSocket: WebSocketClient

    override fun execute(request: CoinSocketRequest): Flow<ResultStatus<CoinSocketResponse>> {
        connectWebSocket(request)
        return flow
    }

    private fun connectWebSocket(request: CoinSocketRequest) {
        webSocket = object : WebSocketClient(URI(url)) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                val json = coinSocketRequestJsonAdapter.toJson(request)
                webSocket.send(json)
            }

            override fun onMessage(message: String?) {
                message?.let { json ->
                    coinSocketResponseJsonAdapter.fromJson(json.replaceFirst("onMessage", ""))
                        ?.let {
                            CoroutineScope(Dispatchers.Main).launch {
                                flow.emit(ResultStatus.Success(it))
                            }
                        }
                }
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
            }

            override fun onError(ex: Exception?) {
                CoroutineScope(Dispatchers.Main).launch {
                    flow.emit(ResultStatus.Exception(ServiceException(ex?.message.toString())))
                }
            }
        }
        webSocket.connect()
    }
}