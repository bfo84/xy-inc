package br.com.zup.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@SequenceGenerator(name = "coordinate_seq", sequenceName = "coordinate_seq")
public class Coordinate implements Serializable {

	private static final long serialVersionUID = 3025166137897443273L;

	private Long id;

	private Double x;

	private Double y;

	private String poiName;

	private Double range;

	@Version
	private Integer version;

	public Coordinate() {
	}

	public Coordinate(Double x, Double y, String poiName, Double range) {
		super();
		this.x = x;
		this.y = y;
		this.poiName = poiName;
		this.range = range;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coordinate_seq")
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	@Column(name = "y")
	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	@Column(name = "poiName")
	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	@Transient
	public Double getRange() {
		return range;
	}

	public void setRange(Double range) {
		this.range = range;
	}

	@Transient
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((poiName == null) ? 0 : poiName.hashCode());
		result = prime * result + ((range == null) ? 0 : range.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		Coordinate other = (Coordinate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (poiName == null) {
			if (other.poiName != null)
				return false;
		} else if (!poiName.equals(other.poiName))
			return false;
		if (range == null) {
			if (other.range != null)
				return false;
		} else if (!range.equals(other.range))
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [id=" + id + ", x=" + x + ", y=" + y + ", poiName=" + poiName + ", range=" + range + "]";
	}

}
