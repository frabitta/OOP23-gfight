package gfight.common.api;

import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;

public interface Vect extends Vector<Euclidean2D>{
    Vect norm();

    Vect sum(Vect vector);

    Vect scale(double value);

    Vect revert();

    double getX();

    double getY();
}
