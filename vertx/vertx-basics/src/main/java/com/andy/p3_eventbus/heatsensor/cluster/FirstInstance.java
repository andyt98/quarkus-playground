package com.andy.p3_eventbus.heatsensor.cluster;

import com.andy.p3_eventbus.heatsensor.HeatSensor;
import com.andy.p3_eventbus.heatsensor.HttpServer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstInstance {

    private static final Logger logger = LoggerFactory.getLogger(FirstInstance.class);

    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions(), ar -> {
            if (ar.succeeded()) {
                logger.info("First instance has been started");
                Vertx vertx = ar.result();
                vertx.deployVerticle(HeatSensor.class.getName(), new DeploymentOptions().setInstances(4));
                vertx.deployVerticle(HttpServer.class.getName());
            } else {
                logger.error("Could not start", ar.cause());
            }
        });
    }
}
