package restvideogames.restsql;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Videogame 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private Platform platform;
	private Rating rating;
	private Boolean isCollection;
	private Boolean isFangame;
	private Boolean isFlash;
	private Boolean isFavourite;
	
	public Videogame() {
		
	}
	
	public Videogame(String title, Platform platform, Rating rating, Boolean isCollection, Boolean isFangame, Boolean isFlash, Boolean isFavourite) {
		if(title.length() > 200) {
			throw new IllegalArgumentException("Field 'Title' cannot be longer than 200 characters.");
		}
		this.title = title;
		this.platform = platform;
		this.rating = rating;
		this.isCollection = isCollection;
		this.isFangame = isFangame;
		this.isFlash = isFlash;
		this.isFavourite = isFavourite;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title.length() > 200) {
			throw new IllegalArgumentException("Field 'Title' cannot be longer than 200 characters.");
		}
		this.title = title;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Boolean isCollection() {
		return isCollection;
	}

	public void setCollection(Boolean isCollection) {
		this.isCollection = isCollection;
	}

	public Boolean isFangame() {
		return isFangame;
	}

	public void setFangame(Boolean isFangame) {
		this.isFangame = isFangame;
	}
	
	public Boolean isFlash() {
		return isFlash;
	}

	public void setFlash(Boolean isFlash) {
		this.isFlash = isFlash;
	}

	public Boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(Boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videogame other = (Videogame) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		String toString = String.format("%d.\t%s - Platform: %s - Rating: %s",this.id,this.title,this.platform.name(),this.rating.name());
		if(isCollection()) {
			toString += "\t(Collection)";
		}
		if(isFangame()) {
			toString += "\t(Fangame)";
		}
		if(isFlash()) {
			toString += "\t(Flash)";
		}
		if(isFavourite()) {
			toString += "\t(★)";
		}
		return toString;
	}
	
	public String toConsole() {
		String toString = String.format("\u001B[33m%d.\t\u001B[37m%s \u001B[36m- Platform:\u001B[37m %s \u001B[36m- Rating:\u001B[37m %s",
				this.id,this.title,this.platform.name(),this.rating.name());
		if(isCollection()) {
			toString += "\u001B[32m\t(Collection)\u001B[37m";
		}
		if(isFangame()) {
			toString += "\u001B[35m\t(Fangame)\u001B[37m";
		}
		if(isFlash()) {
			toString += "\u001B[31m\t(Flash)\u001B[37m";
		}
		if(isFavourite()) {
			toString += "\u001B[33m\t(★)\u001B[37m";
		}
		return toString;
	}
	
	public String[] toTable() {
		return String.format("%d;%s;%s;%s;%s;%s;%s",this.id,this.title,this.platform.name(),this.rating.name(),
				this.isCollection	? "X" : " ",
				this.isFangame		? "X" : " ",
				this.isFlash		? "X" : " ",
				this.isFavourite	? "★" : " ").split(";");
	}
}
