package com.moyan.example.j2se.reflect.annotation;


public class AnnotationDto {

	@FruitName("apple")
	private String fruitName;
	@FruitColor(fruitColor= FruitColor.Color.GREEN)
	private String fruitColor;
	
	@FruitName
	private String defaultName;
	@FruitColor
	private String defaultColor;
	
	@Override
	public String toString() {
		return "AnnotationDto [fruitName=" + fruitName + ", fruitColor="
				+ fruitColor + ", defaultName=" + defaultName
				+ ", defaultColor=" + defaultColor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultColor == null) ? 0 : defaultColor.hashCode());
		result = prime * result
				+ ((defaultName == null) ? 0 : defaultName.hashCode());
		result = prime * result
				+ ((fruitColor == null) ? 0 : fruitColor.hashCode());
		result = prime * result
				+ ((fruitName == null) ? 0 : fruitName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnotationDto other = (AnnotationDto) obj;
		if (defaultColor == null) {
			if (other.defaultColor != null)
				return false;
		} else if (!defaultColor.equals(other.defaultColor))
			return false;
		if (defaultName == null) {
			if (other.defaultName != null)
				return false;
		} else if (!defaultName.equals(other.defaultName))
			return false;
		if (fruitColor == null) {
			if (other.fruitColor != null)
				return false;
		} else if (!fruitColor.equals(other.fruitColor))
			return false;
		if (fruitName == null) {
			if (other.fruitName != null)
				return false;
		} else if (!fruitName.equals(other.fruitName))
			return false;
		return true;
	}
}
