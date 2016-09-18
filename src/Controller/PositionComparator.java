package Controller;

import java.util.Comparator;

public class PositionComparator implements Comparator<String> {
	String type;

	public PositionComparator(String type) {
		this.type = type;
	}

	@Override
	public int compare(String no1, String no2) {
		if (type.equals("int")) {
			Integer numero1 = null;
			boolean isNo1Number = true;
			Integer numero2 = null;
			boolean isNo2Number = true;

			try {
				numero1 = Integer.parseInt(no1);
			} catch (NumberFormatException e) {
				isNo1Number = false;
			}

			try {
				numero2 = Integer.parseInt(no2);
			} catch (NumberFormatException e) {
				isNo2Number = false;
			}

			if (isNo1Number && isNo2Number) {
				return numero1.compareTo(numero2);
			} else if (isNo1Number) {
				return 1;
			} else if (isNo2Number) {
				return -1;
			} else {
				return no1.compareTo(no2);
			}
		}
		else if(type.equals("double")){
			Integer numero1 = null;
			boolean isNo1Number = true;
			Integer numero2 = null;
			boolean isNo2Number = true;

			try {
				numero1 = (int)(Double.parseDouble(no1)*100);
			} catch (NumberFormatException e) {
				isNo1Number = false;
			}

			try {
				numero2 = (int)(Double.parseDouble(no2)*100);
			} catch (NumberFormatException e) {
				isNo2Number = false;
			}

			if (isNo1Number && isNo2Number) {
				return numero1.compareTo(numero2);
			} else if (isNo1Number) {
				return 1;
			} else if (isNo2Number) {
				return -1;
			} else {
				return no1.compareTo(no2);
			}
		}
		return 0;
	}
}