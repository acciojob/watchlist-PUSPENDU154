package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private Map<String,Movie> movieMap;
    private Map<String,Director> directorMap;
    private Map<String,List<String>> directorMovieMapping;

    public MovieRepository() {
        this.movieMap = new HashMap<String,Movie>();
        this.directorMap = new HashMap<String,Director>();
        this.directorMovieMapping = new HashMap<String,List<String>>();
    }


    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movieName,String directorName){
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
            movieMap.put(movieName,movieMap.get(movieName));
            directorMap.put(directorName,directorMap.get(directorName));
            List<String> currentMovies=new ArrayList<>();
            if(directorMovieMapping.containsKey(directorName)){
                currentMovies=directorMovieMapping.get(directorName);
            }
            currentMovies.add(movieName);
            directorMovieMapping.put(directorName,currentMovies);
        }
    }

    public Movie findMovie(String movieName){
       return movieMap.get(movieName);
    }

    public Director findDirector(String directorName){
        return directorMap.get(directorName);
    }

    public List<String> findAllMoviesByDirector(String directorName){
        List<String> findMovies=new ArrayList<>();
        if(directorMovieMapping.containsKey(directorName)){
            findMovies=directorMovieMapping.get(directorName);
        }
        return findMovies;
    }

    public List<String> getAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String directorName){
        List<String> movies=new ArrayList<>();
        if(directorMovieMapping.containsKey(directorName)){
            movies=directorMovieMapping.get(directorName);
            for(String movie:movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            directorMovieMapping.remove(directorName);
        }
        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
    }

    public void deleteAllDirector(){



        for(String director:directorMovieMapping.keySet()){
            for(String movie:directorMovieMapping.get(director)){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            if(directorMap.containsKey(director)){
                directorMap.remove(director);
            }

            directorMovieMapping.remove(director);
        }
    }




}
