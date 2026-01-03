package com.example._Do.config;

import io.github.bucket4j.distributed.ExpirationAfterWriteStrategy;
import io.github.bucket4j.distributed.proxy.ClientSideConfig;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class RateLimitingConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${spring.data.redis.ssl.enabled}")
    private boolean redisSsl;

    @Bean
    public RedisClient redisClient() {
        RedisURI.Builder builder = RedisURI.builder()
                .withHost(redisHost)
                .withPort(redisPort)
                .withSsl(redisSsl);

        if (redisPassword != null && !redisPassword.isBlank()) {
            builder.withPassword(redisPassword.toCharArray());
        }

        return RedisClient.create(builder.build());
    }

    @Bean
    public ProxyManager<String> proxyManager(RedisClient redisClient) {
        StatefulRedisConnection<String, byte[]> connection = redisClient.connect(
                RedisCodec.of(StringCodec.UTF8, ByteArrayCodec.INSTANCE)
        );

        ClientSideConfig clientSideConfig = ClientSideConfig.getDefault().
                withExpirationAfterWriteStrategy(ExpirationAfterWriteStrategy.basedOnTimeForRefillingBucketUpToMax(
                        Duration.ofHours(1)
                ));

        return LettuceBasedProxyManager.builderFor(connection)
                .withClientSideConfig(clientSideConfig)
                .build();

    }

}
