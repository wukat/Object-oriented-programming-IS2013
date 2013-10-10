/**
 * Punkt3D.java
 * @author - wukat
 * @data - 10 paź 2013
 */
package lab2;

/**
 * @author wukat
 * 
 */
public class Punkt3D extends Punkt2D {

	/**
	 * z coordinator
	 */
	double z;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Punkt3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	/**
	 * Getter
	 * 
	 * @return the z
	 */
	public double getZ() {
		return z;
	}

	/**
	 * z setter
	 * 
	 * @param z
	 *            the z to set
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Distance calculator
	 * 
	 * @param point
	 *            3D point
	 * @return distance between two points
	 */
	public double distance(Punkt3D point) {
		return Math.sqrt((getX() - point.getX()) * (getX() - point.getX())
				+ (getY() - point.getY()) * (getY() - point.getY())
				+ (getZ() - point.getZ()) * (getZ() - point.getZ()));
	}
}
