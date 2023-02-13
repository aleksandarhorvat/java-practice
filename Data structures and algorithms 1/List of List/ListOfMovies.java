/*
Create a class ListOfMovies. Store individual movies in a singly linked list.
Remember the title (string) for each movie.
Add so that the movies in the list are unique by name.

Extend the class representing a movie to have a list of actors associated with that movie.
Each actor is represented by a single string of type "First Name Last Name".
Create methods that add actors to movies by name.
An actor can only appear once in one film. If the movie does not exist, give up adding.
Print all the movies and the actors in them neatly on the screen

Add a method to the ListMovies class that deletes all movies starring the given actor.
*/

class ListOfMovies {

    private Movie firstMovie;

    class Movie {
        String name;
        Movie relation;
        Actor content;

        public Movie(String name) {
            this.name = name;
            relation = null;
        }

        public String toString() {
            String result = "\n[Name of the movie: " + name;
            Actor current = content;
            while (current != null) {
                result += " " + current;
                current = current.relation;
            }
            result += "]\n---------------------------------------------------------";
            return result;
        }
    }

    class Actor {
        String nameSurname;
        Actor relation;

        public Actor(String nameSurname) {
            this.nameSurname = nameSurname;
            relation = null;
        }

        public String toString() {
            return "\n[Actor: " + nameSurname + "]";
        }
    }

    public String toString() {
        String result = "Movies:\n---------------------------------------------------------";
        Movie current = firstMovie;
        while (current != null) {
            result += " " + current;
            current = current.relation;
        }
        return result;
    }

    public void addMovie(String newMovie) {
        if (!movieExist(newMovie)) {
            Movie movie = new Movie(newMovie);
            movie.relation = firstMovie;
            firstMovie = movie;
        }
    }

    public boolean movieExist(String movie) {
        Movie current = firstMovie;
        while (current != null) {
            if (current.name.equals(movie)) {
                return true;
            }
            current = current.relation;
        }
        return false;
    }

    private Movie findMovie(String movie) {
        Movie current = firstMovie;
        while (current != null) {
            if (current.name.equals(movie)) {
                return current;
            }
            current = current.relation;
        }
        return null;
    }

    private boolean findActor(Movie movie, String actor) {
        Actor currentActor = movie.content;
        while (currentActor != null) {
            if (currentActor.nameSurname.equals(actor)) {
                return true;
            }
            currentActor = currentActor.relation;
        }
        return false;
    }

    //Add actors to movies by name.
    //An actor can only appear once in one film. If the movie does not exist, give up adding.
    public void addActor(String movie, String newActor) {
        Movie goal = findMovie(movie);
        if (goal == null) {
            return;
        }
        if (!findActor(goal, newActor)) {
            Actor actor = new Actor(newActor);
            actor.relation = goal.content;
            goal.content = actor;
        }
    }

    //Method that deletes all movies starring the given actor.
    public void removeActor(String nameActor) {
        Movie curr = firstMovie;
        while (curr != null) {
            Actor currentActor = curr.content;
            while (currentActor != null) {
                if (currentActor.nameSurname.equals(nameActor)) {
                    removeMovie(curr);
                    break;
                } else
                    currentActor = currentActor.relation;
            }
            curr = curr.relation;
        }
    }

    public void removeMovie(Movie movie) {
        if (firstMovie != null && firstMovie == movie) {
            firstMovie = firstMovie.relation;
            return;
        } else {
            Movie current, previous;
            current = firstMovie;
            previous = null;
            while (current != null && current != movie) {
                previous = current;
                current = current.relation;
            }
            if (current != null)
                previous.relation = current.relation;
        }
    }
}
