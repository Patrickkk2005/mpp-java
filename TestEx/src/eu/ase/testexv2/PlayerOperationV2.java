package eu.ase.testexv2;

import eu.ase.testex.Player;

import java.util.List;

@FunctionalInterface
public interface PlayerOperationV2 {
    double process(List<PlayerV2> list);
}