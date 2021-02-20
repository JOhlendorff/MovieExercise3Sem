package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieByID(@PathParam("id") int id){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getById(id)); 
        } finally {
            em.close();
        }
    }
    @GET
    @Path("title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieByTitle(@PathParam("title") String title){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getByName(title)); 
        } finally {
            em.close();
        }
    }
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieAll(){
        EntityManager em = EMF.createEntityManager();
        try {
            return new Gson().toJson(FACADE.getAll());
        } finally {
            em.close();
        }
    }
}
