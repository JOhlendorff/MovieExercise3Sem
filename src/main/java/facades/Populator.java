/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.MovieDTO;
import entities.Movie;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade fe = MovieFacade.getFacadeExample(emf);
        String[] actors1 = {"Lars as Picard", "thomas", "jon"};
        String[] actors2 = {"Lars as Xavier", "thomas", "jon"};
        String[] actors3 = {"Lars as Scrooge", "jesper", "jonatan"};

        fe.create(new MovieDTO(new Movie(actors1, 1970, "Star Trek TNG")));
        fe.create(new MovieDTO(new Movie(actors2, 1950, "X-Men: Days of Future past ")));
        fe.create(new MovieDTO(new Movie(actors3, 1990, "Christmas Carol")));
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
