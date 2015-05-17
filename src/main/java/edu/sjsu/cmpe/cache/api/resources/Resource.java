package edu.sjsu.cmpe.cache.api.resources;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;
import edu.sjsu.cmpe.cache.domain.Entry;
import edu.sjsu.cmpe.cache.repository.CacheInterface;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {
    private final CacheInterface cache;

    /**
     * Resource constructor
     * 
     * @param cache
     *            a InMemoryCache instance
     */
    public Resource(CacheInterface cache) {
        this.cache = cache;
    }

    @GET
    @Path("{key}")
    @Timed(name = "Get Entry")
    public Entry get(@PathParam("key") LongParam key) {
        return cache.get(key.get().intValue());
    }

    @GET
    @Timed(name = "View All Entries")
    public List<Entry> getAll() {
        return cache.getAll();
    }

    @PUT
    @Path("{key}/{value}")
    @Timed(name = "Add Entry")
    public Response put(@PathParam("key") LongParam key,
            @PathParam("value") String value) {
        Entry entry = new Entry();
        entry.setKey(key.get().intValue());
        entry.setValue(value);

        cache.save(entry);

        return Response.status(200).build();
    }
}
