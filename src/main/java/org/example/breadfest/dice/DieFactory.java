package org.example.breadfest.dice;

import org.example.breadfest.dice.Common.NormalDie;
import org.example.breadfest.dice.Epic.TripleNormalDie;
import org.example.breadfest.dice.Nuclear.AtomicDie;
import org.example.breadfest.dice.Rare.DoubleNormalDie;
import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurTypes;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DieFactory {

    public Dice DieFactory(DinosaurTypes dinosaur_type) throws Exception {

        return switch (dinosaur_type) {
            case Rare -> new DoubleNormalDie();
            case Epic -> new TripleNormalDie();
            case Nuclear -> new AtomicDie();
            default -> new NormalDie();
        };

    }
}
