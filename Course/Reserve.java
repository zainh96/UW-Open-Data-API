package Course;

/**
 * Created by ZainH on 06/09/2015.
 */
public class Reserve {
    private String reserveGroup = null;
    private int enrollmentCapacity = 0;
    private int enrollmentTotal = 0;

    public String getReserveGroup() {
        return reserveGroup;
    }

    public void setReserveGroup(String reserveGroup) {
        this.reserveGroup = reserveGroup;
    }

    public int getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    public void setEnrollmentCapacity(int enrollmentCapacity) {
        this.enrollmentCapacity = enrollmentCapacity;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void setEnrollmentTotal(int enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }
}
