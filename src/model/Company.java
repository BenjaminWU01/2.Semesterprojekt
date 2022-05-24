package model;

public class Company {

	private int CVRno;
	private int EANno;

	public Company(int CVRno, int EANno) {
		this.CVRno = CVRno;
		this.EANno = EANno;
	}

	public int getCVRno() {
		return CVRno;
	}

	public void setCVRno(int cVRno) {
		CVRno = cVRno;
	}

	public int getEANno() {
		return EANno;
	}

	public void setEANno(int eANno) {
		EANno = eANno;
	}
}
