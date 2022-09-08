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

    @Test
    fun testOpsForHash () {
        redisTemplate.opsForHash<String, Any>().put("me", "name", "zzz")
        println(redisTemplate.opsForHash<String, Any>().entries("me"))
        assertEquals(redisTemplate.opsForHash<String, Any>().get("me", "name"), "zzz")
        val map = HashMap<String, Any>()
        map["name"] = "zzz1"
        map["age"] = 18
        redisTemplate.opsForHash<String,Any>().putAll("myBro", map)
        println(redisTemplate.opsForHash<String, Any>().entries("myBro"))
        assertEquals(redisTemplate.opsForHash<String, Any>().get("myBro", "age"), 18)
        assertEquals(redisTemplate.opsForHash<String, Any>().entries("myBro").entries, map.entries)
    }
}
