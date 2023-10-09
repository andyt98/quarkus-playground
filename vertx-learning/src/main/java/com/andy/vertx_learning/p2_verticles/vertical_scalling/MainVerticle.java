package com.andy.vertx_learning.p2_verticles.vertical_scalling;

import com.andy.vertx_learning.p2_verticles.hello.HelloVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloVerticle.class);

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }

    @Override
    public void start(final Promise<Void> startPromise) {
        LOGGER.debug("Start " + getClass().getName());
        vertx.deployVerticle(VerticleN.class.getName(),
                new DeploymentOptions()
                        .setInstances(4)
                        .setConfig(new JsonObject()
                                .put("id", UUID.randomUUID().toString())
                                .put("name", VerticleN.class.getSimpleName())
                        )
        );
        startPromise.complete();
    }
}
