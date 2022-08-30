package redisTemplate

import com.willing.springbootredisusage.SpringBootRedisUsageApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import javax.annotation.Resource

/**
 * 基础测试用例写法
 */
@SpringBootTest(classes = [SpringBootRedisUsageApplication::class])
class BasicUsageTests {
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Test
    fun testOpsForValue() {
        val name = "testK"
        val value = "testV"
        this.redisTemplate.opsForValue().set(name, value)
        val valueFromRedis = this.redisTemplate.opsForValue().get(name)
        assertEquals(valueFromRedis, value)
    }
}
