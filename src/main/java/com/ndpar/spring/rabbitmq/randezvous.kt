package com.ndpar.spring.rabbitmq

import org.apache.commons.codec.digest.DigestUtils
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE as JSON

private const val TIMEOUT = 20_000L

data class Message(
    @NotNull val id: String,
    @NotNull val message: String,
    val date: Date?
)

@RestController
@RequestMapping(path = ["/alice"])
class FirstController(
    @Autowired private val service: MessageService
) {
    @PostMapping(consumes = [JSON], produces = [JSON])
    fun process(@Valid @RequestBody message: Message): Message {
        val body = service.receive(message.id)
        return if (body != null) {
            service.send(message.id.replyTo(), message.message)
            message.copy(message = body, date = Date())
        } else {
            message.copy(message = "TIMEOUT")
        }
    }
}

@Validated
@RestController
@RequestMapping(path = ["/bob"])
class SecondController(
    @Autowired private val service: MessageService
) {
    @PostMapping(consumes = [JSON], produces = [JSON])
    fun process(@Valid @RequestBody message: Message): Message {
        service.send(message.id, message.message)
        val body = service.receive(message.id.replyTo())
        return if (body != null) {
            message.copy(message = body, date = Date())
        } else {
            message.copy(message = "TIMEOUT")
        }
    }
}

interface MessageService {
    fun send(to: String, text: String)
    fun receive(from: String): String?
}

@Service
class RuntimeExchangeCreator(
    @Autowired private val admin: RabbitAdmin,
    @Autowired private val template: RabbitTemplate
) : MessageService {

    override fun send(to: String, text: String) {
        val queue = createQueue(to)
        val exchange = DirectExchange(to)
        with(admin) {
            declareQueue(queue)
            declareExchange(exchange)
            declareBinding(BindingBuilder.bind(queue).to(exchange).with(to))
        }
        template.convertAndSend(to, to, text)
    }

    override fun receive(from: String): String? {
        admin.declareQueue(createQueue(from))
        return template.receiveAndConvert(from, TIMEOUT) as String?
    }

    private fun createQueue(name: String) = Queue(name, false, false, true, mapOf("x-expires" to TIMEOUT))
}

private fun String.replyTo(): String = DigestUtils.sha256Hex(this)