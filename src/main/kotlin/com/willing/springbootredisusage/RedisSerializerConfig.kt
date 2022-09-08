package com.willing.springbootredisusage

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.connectionFactory = connectionFactory

        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Object::class.java)
        val objectMapper = ObjectMapper()
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper)

        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = jackson2JsonRedisSerializer
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = jackson2JsonRedisSerializer
        redisTemplate.afterPropertiesSet()
        return redisTemplate
    }
}
