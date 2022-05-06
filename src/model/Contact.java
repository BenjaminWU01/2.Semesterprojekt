package model;

public class Contact {

		private String name;
		private String address;
		private String zipcode;
		private String countrycode;
		private String city;
		private String mainProneNo;
		private String mainEmail;
		
		public Contact(String name, String address, String zipcode, String countrycode, String city, String mainProneNo,
				String mainEmail) {
			super();
			this.name = name;
			this.address = address;
			this.zipcode = zipcode;
			this.countrycode = countrycode;
			this.city = city;
			this.mainProneNo = mainProneNo;
			this.mainEmail = mainEmail;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public String getCountrycode() {
			return countrycode;
		}

		public void setCountrycode(String countrycode) {
			this.countrycode = countrycode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getMainProneNo() {
			return mainProneNo;
		}

		public void setMainProneNo(String mainProneNo) {
			this.mainProneNo = mainProneNo;
		}

		public String getMainEmail() {
			return mainEmail;
		}

		public void setMainEmail(String mainEmail) {
			this.mainEmail = mainEmail;
		}
		
		
		
}
