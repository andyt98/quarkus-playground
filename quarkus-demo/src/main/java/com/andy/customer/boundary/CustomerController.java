package com.andy.customer.boundary;

import com.andy.customer.control.CustomerService;
import com.andy.customer.entity.CustomerDto;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.security.Security;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Path("/customers.html")
@Produces(MediaType.TEXT_HTML)
public class CustomerController {

    @Location("customers.html")
    Template customersTemplate;

    @Inject
    CustomerService customerService;

    @Inject
    SecurityIdentity identity;

    @GET
    @Blocking
    public TemplateInstance customers() {
        List<CustomerDto> customers = customerService.findAll();

        System.out.println("identity.getPrincipal().getName() = " + identity.getPrincipal().getName());
        System.out.println("identity.getRoles() = " + identity.getRoles());
        System.out.println("identity.getAttributes() = " + identity.getAttributes());

        return customersTemplate.data(
                "customers", customers,
                "user", identity);
    }

    @TemplateExtension
    static String format(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).withNano(0)
                .format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

}
