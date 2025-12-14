import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieRecommender {

    static class Movie {
        private String title;
        private String genre;

        public Movie(String title, String genre) {
            this.title = title;
            this.genre = genre;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
        }

        @Override
        public String toString() {
            return title + " [" + genre + "]";
        }
    }

    public static List<Movie> recommendByGenre(List<Movie> movies, String genre) {
        List<Movie> recommended = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getGenre().equalsIgnoreCase(genre)) {
                recommended.add(m);
            }
        }
        return recommended;
    }

    public static void main(String[] args) {
        List<Movie> movieDatabase = new ArrayList<>();
        // Horror
        movieDatabase.add(new Movie("The Conjuring", "Horror"));
        movieDatabase.add(new Movie("It", "Horror"));
        movieDatabase.add(new Movie("Insidious", "Horror"));
        movieDatabase.add(new Movie("Annabelle", "Horror"));
        movieDatabase.add(new Movie("The Shining", "Horror"));
        // Action
        movieDatabase.add(new Movie("The Matrix", "Action"));
        movieDatabase.add(new Movie("Avengers: Endgame", "Action"));
        // Sci-Fi
        movieDatabase.add(new Movie("Inception", "Sci-Fi"));
        movieDatabase.add(new Movie("Interstellar", "Sci-Fi"));
        // Romance
        movieDatabase.add(new Movie("Titanic", "Romance"));
        movieDatabase.add(new Movie("The Notebook", "Romance"));
        movieDatabase.add(new Movie("La La Land", "Romance"));
        // Crime
        movieDatabase.add(new Movie("The Godfather", "Crime"));
        movieDatabase.add(new Movie("Pulp Fiction", "Crime"));
        // Animation
        movieDatabase.add(new Movie("Finding Nemo", "Animation"));
        movieDatabase.add(new Movie("Coco", "Animation"));
        // Thriller
        movieDatabase.add(new Movie("Parasite", "Thriller"));
        movieDatabase.add(new Movie("Shutter Island", "Thriller"));
        // Comedy
        movieDatabase.add(new Movie("3 Idiots", "Comedy"));
        movieDatabase.add(new Movie("Hera Pheri", "Comedy"));
        movieDatabase.add(new Movie("The Hangover", "Comedy"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Movie Recommender System!");
        System.out.println("Tell us what kind of movie you want to watch today (e.g., Horror, Action, Romance, Thriller, Comedy):");
        String userGenre = scanner.nextLine();

        List<Movie> suggestions = recommendByGenre(movieDatabase, userGenre);

        if (suggestions.isEmpty()) {
            System.out.println("Sorry, we couldn't find any movies in the '" + userGenre + "' genre.");
        } else {
            System.out.println("Based on your interest, you might enjoy these " + userGenre + " movies:");
            for (int i = 0; i < suggestions.size() && i < 5; i++) {
                System.out.println("" + suggestions.get(i));
            }
        }

        scanner.close();
    }
}
