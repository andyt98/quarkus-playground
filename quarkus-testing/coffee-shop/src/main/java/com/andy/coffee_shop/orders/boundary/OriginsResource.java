package com.andy.coffee_shop.orders.boundary;

import com.andy.coffee_shop.orders.entity.CoffeeType;
import com.andy.coffee_shop.orders.control.EntityBuilder;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Unremovable // https://github.com/quarkusio/quarkus/issues/5314
public class OriginsResource {

    @Inject
    CoffeeShop coffeeShop;

    @PathParam("type")
    CoffeeType type;

    @Context
    UriInfo uriInfo;

    @Inject
    EntityBuilder entityBuilder;

    @GET
    public JsonArray getOrigins() {
        return coffeeShop.getOrigins(type).stream()
                .map(o -> entityBuilder.buildOrigin(uriInfo, o, type))
                .collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add).build();
    }

}
