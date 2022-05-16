package model;

public class Size {

	private String sizeDesc;
	private int idSize;

	public Size(String sizeDesc, int idSize) {
		super();
		this.sizeDesc = sizeDesc;
		this.idSize = idSize;
	}

	public String getSizeDesc() {
		return sizeDesc;
	}

	public void setSizeDesc(String sizeDesc) {
		this.sizeDesc = sizeDesc;
	}

	public int getIdSize() {
		return idSize;
	}

}
