package eu.ase.claudeex;

import java.util.List;

@FunctionalInterface
public interface MovieOperation {
    double process(List<Movie> movies);
}
