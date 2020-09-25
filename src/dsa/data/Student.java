package dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	private String first;
	private String last;
	private int id;
	private int creditHours;
	private double gpa;
	private String unityId;
	
	/**
	 * Student constructor 
	 * @param first student's first name
	 * @param last student's last name
	 * @param id student's id
	 * @param creditHours credit hours
	 * @param gpa student's gpa
	 * @param unityId unity ID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityId) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityId = unityId;
	}

	/**
	 * This method return student's first name
	 * @return student's first name
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * This method set the student's first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * This method return student's last name
	 * @return the last name
	 */
	public String getLast() {
		return last;
	}

	/**
	 * This method set the student's last name
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * This method return student's ID
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method set the student's ID
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method returns credit hours
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * This method set the student's credit hours
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * return GPA
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * This method set the student's GPA
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * This method return the unity ID
	 * @return the unityId
	 */
	public String getUnityID() {
		return unityId;
	}

	/**
	 * Set the unity ID
	 * @param unityId the unityId to set
	 */
	public void setUnityID(String unityId) {
		this.unityId = unityId;
	}

	@Override
	public int compareTo(Student o) {
		if(!last.equals(o.last)){
			return last.compareTo(o.last);
		} else if(!first.equals(o.first)){
			return first.compareTo(o.first);
		} else if(o.id != id) {
			return Integer.compare(this.id, o.id);
		}
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, id, last);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		return Objects.equals(first, other.first) && id == other.id && Objects.equals(last, other.last);
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}
