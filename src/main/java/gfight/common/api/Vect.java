package gfight.common.api;

import gfight.common.Position2D;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;

public interface Vect extends Vector<Euclidean2D>{
    Vect norm();

    Vect add(Vect vector);

    Vect scale(double value);

    Vect revert();

    double getX();

    double getY();
}
