package com.ameda.kev.paginationcursorkeyset.rateLimiting.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Author: kev.Ameda
 */
@Service
@Slf4j
public class RateLimitingService {

    private final static  int  REQUEST_PER_MINUTE = 10;

    //Storage for buckets (IP address -> buckets )
    //Proxy manager is responsible for talking to redis and returning the bucket states
    private final ProxyManager<String> proxyManager;

    public RateLimitingService(ProxyManager<String> proxyManager) {
        this.proxyManager = proxyManager;
    }
                /**
                * This method will be called for every incoming request.
                 * The key here can be an IP address, database primary key etc
                * */

    public Bucket resolveBucket(String key){
        Supplier<BucketConfiguration> supplierConfig = this::getConfig;
        // simply asks redis for a bucket for the specified key, which is returned if is there
        // else Bucket4j creates a new bucket using getConfig() and saves it in redis.
        return proxyManager.builder()
                .build(key, supplierConfig);
    }

    /**
    * Acts as factory  that produces BucketConfiguration objects
     * for newly created objects <br/>
     * Handles the following : <br/>
     * - How many tokens a bucket can hold. <br/>
     * - How quickly tokens are filled.
     *
     *
     *
    * */
    private BucketConfiguration getConfig(){
        Bandwidth limit = Bandwidth.builder()
                .capacity(REQUEST_PER_MINUTE)
                .refillIntervally(REQUEST_PER_MINUTE, Duration.ofMinutes(1))
                .build();
        return BucketConfiguration.builder()
                .addLimit(limit)
                .build();
    }
}
